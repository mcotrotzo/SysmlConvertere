package org.example.Containers;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.*;
import org.example.Mapping.NewVersion.NoMappedElementException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Util.LibraryPackageNames;
import org.example.Util.TwinLibraryNamespace;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public final class ContainerManager {

	private final Map<String, List<Class<?>>> containers = new HashMap<>();
	private final Utils utils;

	public ContainerManager(Utils utils) {
		this.utils = utils;
		scan();
	}

	private void scan() {
		try (ScanResult scanResult = new ClassGraph().acceptPackages("org.example").enableAllInfo().scan()) {

			for (var annotationInfo : scanResult.getAllAnnotations()) {

				String annotationName = annotationInfo.getName();

				for (ClassInfo annotatedClass : scanResult.getClassesWithAnnotation(annotationName)) {
					registerIfConcrete(annotationName, annotatedClass);
					for (ClassInfo subclass : annotatedClass.getSubclasses()) {

						registerIfConcrete(annotationName, subclass);
					}
				}
			}
		}
	}

	private void registerIfConcrete(String annotationName, ClassInfo classInfo) {
		if (classInfo.isInterface() || classInfo.isAbstract()) {
			return;
		}

		containers.computeIfAbsent(annotationName, ignored -> new ArrayList<>()).add(classInfo.loadClass());
	}


	public List<Class<? extends MappedNamespaceElement<?, ?>>> getLibraryMappedClasses() {
		return getMappedNamespaceClasses(MappedElementType.class);
	}

	public List<Class<? extends MappedNamespaceElement<?, ?>>> getTypeMetaclassMappedClasses() {
		return getMappedNamespaceClasses(MappedMetaclass.class);
	}

	public List<Class<? extends MappedNamespaceElement<?, ?>>> getNamespaceMetaclassMappedClasses() {
		return getMappedNamespaceClasses(MappedMetaclass.class);
	}

	public List<Class<? extends PackageElementType>> getPackageMappedClasses() {
		return containers.getOrDefault(PackageTypeMeta.class.getName(), Collections.emptyList()).stream().filter(PackageElementType.class::isAssignableFrom).filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).map(this::castPackageClass).collect(Collectors.toCollection(ArrayList::new));
	}

	private List<Class<? extends MappedElement<?, ?>>> getMappedElementClasses(Class<? extends Annotation> annotation) {
		return containers.getOrDefault(annotation.getName(), Collections.emptyList()).stream().filter(MappedElement.class::isAssignableFrom).filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).map(this::castMappedElementClass).collect(Collectors.toCollection(ArrayList::new));
	}

	private List<Class<? extends MappedNamespaceElement<?, ?>>> getMappedNamespaceClasses(Class<? extends Annotation> annotation) {
		return containers.getOrDefault(annotation.getName(), Collections.emptyList()).stream().filter(MappedNamespaceElement.class::isAssignableFrom).filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).map(this::castMappedNamespaceClass).collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Class<? extends MappedNamespaceElement<?, ?>>> getMappedElementClasses() {

		List<Class<? extends MappedNamespaceElement<?, ?>>> result = getLibraryMappedClasses();

		sortBySysmlTypeSpecificity(result);

		return result;
	}


	private Constructor<?> findCompatibleConstructor(Class<? extends MappedNamespaceElement<?, ?>> mappedClass, Element sysmlElement) {
		Constructor<?> bestConstructor = null;

		for (Constructor<?> constructor : mappedClass.getDeclaredConstructors()) {

			Class<?>[] parameterTypes = constructor.getParameterTypes();

			if (parameterTypes.length != 1) {
				continue;
			}

			Class<?> parameterType = parameterTypes[0];

			if (!parameterType.isInstance(sysmlElement)) {
				continue;
			}

			if (bestConstructor == null) {
				bestConstructor = constructor;
				continue;
			}

			Class<?> currentBestType = bestConstructor.getParameterTypes()[0];

			if (currentBestType.isAssignableFrom(parameterType)) {
				bestConstructor = constructor;
			}
		}

		return bestConstructor;
	}


	public Constructor<? extends MappedNamespaceElement<?, ?>> getMappedConstructor(Element sysmlElement) throws MappingException {

		Objects.requireNonNull(sysmlElement, "sysmlElement");

		if (sysmlElement instanceof Type type) {
			return castNamespaceConstructor(getMappedConstructor(type));
		}

		if (sysmlElement instanceof org.omg.sysml.lang.sysml.Package sysmlPackage) {

			Constructor<? extends MappedNamespaceElement<?, ?>> constructor = findPackageConstructor(sysmlPackage);

			if (constructor != null) {
				return constructor;
			}
		}

		Constructor<? extends MappedNamespaceElement<?, ?>> metaclassConstructor = findNamespaceMetaclassConstructor(sysmlElement);

		if (metaclassConstructor != null) {
			return metaclassConstructor;
		}

		throw new NoMappedElementException("No mapped constructor found for '%s' (%s).".formatted(safeName(sysmlElement), sysmlElement.getClass().getSimpleName()));
	}


	private Constructor<? extends MappedNamespaceElement<?, ?>> findPackageConstructor(
			org.omg.sysml.lang.sysml.Package sysmlPackage
	) throws MappingException {

		Class<? extends PackageElementType> mappedClass;
		if (utils.isFromStandardLibrary(sysmlPackage)){
			return null;
		}

		if(imports(sysmlPackage, LibraryPackageNames.TWIN_DEF_LIBRARY)){
			mappedClass = TwinDefLibraryMapped.class;
		}

		else if(imports(sysmlPackage, LibraryPackageNames.USER_LIBRARY)){
			mappedClass = UserLibraryMapped.class;
		}
		else {
			mappedClass = LibraryPackagesMapped.class;
		}



		Constructor<?> constructor =
				findCompatibleConstructor(
						castMappedNamespaceClass(mappedClass),
						sysmlPackage
				);

		if (constructor == null) {
			throw new MappingException(
					"No compatible package constructor found for '%s' (%s) using %s."
							.formatted(
									safeName(sysmlPackage),
									sysmlPackage.getClass().getSimpleName(),
									mappedClass.getSimpleName()
							)
			);
		}

		return castNamespaceConstructor(constructor);
	}

	private boolean hasName(LibraryPackageNames name,Element sysmlElement) {

		if (sysmlElement.getName() == null) {
			return false;
		}

		return sysmlElement.getQualifiedName().equals(name.toString());
	}

	private boolean imports(
			org.omg.sysml.lang.sysml.Package sysmlPackage,
			LibraryPackageNames libraryPackageName
	) throws MappingException {

		List<Package> importedPackages = new ArrayList<>();

		for (Import importElement : sysmlPackage.getOwnedImport()) {
			if (importElement instanceof NamespaceImport namespaceImport
					&& namespaceImport.getImportedNamespace() instanceof Package packageType) {
				importedPackages.add(packageType);
			} else if (importElement instanceof MembershipImport membershipImport
					&& membershipImport.getImportedMembership() != null
					&& membershipImport.getImportedMembership().getMemberElement() instanceof Package packageType) {
				importedPackages.add(packageType);
			}
		}


		return importedPackages.stream()
				.anyMatch(x -> hasName(libraryPackageName, x));
	}


	public Constructor<? extends MappedNamespaceElement<?, ?>> getMappedConstructor(Type sysmlElement) throws MappingException {

		Objects.requireNonNull(sysmlElement, "sysmlElement");

		Constructor<? extends MappedNamespaceElement<?, ?>> libraryConstructor = findLibraryConstructor(sysmlElement);

		if (libraryConstructor != null) {
			return libraryConstructor;
		}

		Constructor<? extends MappedElement<?, ?>> metaclassConstructor = findTypeMetaclassConstructor(sysmlElement);

		if (metaclassConstructor != null) {
			return metaclassConstructor;
		}

		if (utils.isFromDTLibrary(sysmlElement)) {
			throw new MappingException("No library mapper found for DT library element '%s' (%s)".formatted(sysmlElement.getQualifiedName(), sysmlElement.getClass().getSimpleName()));
		}

		throw new NoMappedElementException("No mapped constructor found for '%s' (%s).".formatted(safeName(sysmlElement), sysmlElement.getClass().getSimpleName()));
	}


	private Constructor<? extends MappedElement<?, ?>> findTypeMetaclassConstructor(Type sysmlElement) throws MappingException {
		Map<Constructor<?>, Class<?>> candidates = new LinkedHashMap<>();

		for (Class<? extends MappedNamespaceElement<?, ?>> mappedClass : getTypeMetaclassMappedClasses()) {

			Constructor<?> constructor = findCompatibleConstructor(castMappedNamespaceClass(mappedClass), sysmlElement);

			if (constructor != null) {
				candidates.put(constructor, constructor.getParameterTypes()[0]);
			}
		}

		candidates.entrySet().removeIf(candidate -> candidates.entrySet().stream().anyMatch(other -> other != candidate && candidate.getValue().isAssignableFrom(other.getValue())));

		if (candidates.size() > 1) {
			throw new MappingException("Multiple metaclass constructors found for '%s' (%s). Candidates: %s".formatted(safeName(sysmlElement), sysmlElement.getClass().getSimpleName(), candidates.values().stream().map(Class::getSimpleName).collect(Collectors.joining(", "))));
		}

		return candidates.keySet().stream().findFirst().map(this::castMappedConstructor).orElse(null);
	}


	private Constructor<? extends MappedNamespaceElement<?, ?>> findNamespaceMetaclassConstructor(Element sysmlElement) {
		Constructor<?> best = null;

		for (Class<? extends MappedNamespaceElement<?, ?>> mappedClass : getNamespaceMetaclassMappedClasses()) {

			Constructor<?> constructor = findCompatibleConstructor(mappedClass, sysmlElement);

			if (constructor == null) {
				continue;
			}

			if (best == null) {
				best = constructor;
				continue;
			}

			Class<?> bestParameter = best.getParameterTypes()[0];

			Class<?> candidateParameter = constructor.getParameterTypes()[0];

			if (bestParameter.isAssignableFrom(candidateParameter)) {
				best = constructor;
			}
		}

		return castNamespaceConstructor(best);
	}


	private boolean isLibraryTypeCompatible(Type sysmlElement, Class<? extends MappedNamespaceElement<?, ?>> mappedClass) {
		Type mappedLibraryType = getMappedLibraryType(mappedClass);

		return mappedLibraryType != null && TypeUtil.isCompatible(sysmlElement, mappedLibraryType);
	}

	private Constructor<? extends MappedNamespaceElement<?, ?>> findLibraryConstructor(Type sysmlElement) throws MappingException {

		List<Class<? extends MappedNamespaceElement<?, ?>>> classes = getLibraryMappedClasses();

		sortBySysmlTypeSpecificity(classes);

		Map<Constructor<?>, Type> candidates = new LinkedHashMap<>();

		for (Class<? extends MappedNamespaceElement<?, ?>> mappedClass : classes) {

			if (!isLibraryTypeCompatible(sysmlElement, mappedClass)) {
				continue;
			}

			Constructor<?> constructor = findCompatibleConstructor(castMappedNamespaceClass(mappedClass), sysmlElement);

			if (constructor != null) {
				candidates.put(constructor, getMappedLibraryType(mappedClass));
			}
		}

		candidates.entrySet().removeIf(candidate -> candidates.entrySet().stream().anyMatch(other -> other != candidate && TypeUtil.specializes(other.getValue(), candidate.getValue())));

		if (candidates.size() > 1) {
			throw new MappingException("Multiple constructors found for '%s' (%s). Available types: %s".formatted(safeName(sysmlElement), sysmlElement.getClass().getSimpleName(), candidates.values().stream().map(Type::getQualifiedName).collect(Collectors.joining(", "))));
		}

		return candidates.keySet().stream().findFirst().map(this::castMappedConstructor).orElse(null);
	}

	private void sortBySysmlTypeSpecificity(List<Class<? extends MappedNamespaceElement<?, ?>>> classes) {
		Map<Class<? extends MappedNamespaceElement<?, ?>>, Type> libraryTypes = new HashMap<>();

		for (var mappedClass : classes) {
			libraryTypes.put(mappedClass, getMappedLibraryType(mappedClass));
		}

		Map<Class<? extends MappedNamespaceElement<?, ?>>, List<Class<? extends MappedNamespaceElement<?, ?>>>> edges = new HashMap<>();

		Map<Class<? extends MappedNamespaceElement<?, ?>>, Integer> indegree = new HashMap<>();

		for (var c : classes) {
			indegree.put(c, 0);
		}

		for (var a : classes) {

			Type typeA = libraryTypes.get(a);

			if (typeA == null) {
				continue;
			}

			for (var b : classes) {

				if (a == b) {
					continue;
				}

				Type typeB = libraryTypes.get(b);

				if (typeB == null) {
					continue;
				}

				if (TypeUtil.specializes(typeA, typeB)) {

					edges.computeIfAbsent(a, k -> new ArrayList<>()).add(b);

					indegree.merge(b, 1, Integer::sum);
				}
			}
		}

		for (var c : classes) {

			if (libraryTypes.get(c) != null) {
				continue;
			}

			for (var other : classes) {

				if (other == c) {
					continue;
				}

				if (libraryTypes.get(other) == null) {
					continue;
				}

				edges.computeIfAbsent(other, k -> new ArrayList<>()).add(c);

				indegree.merge(c, 1, Integer::sum);
			}
		}

		TreeSet<Class<? extends MappedNamespaceElement<?, ?>>> ready = new TreeSet<>(Comparator.comparing(Class::getName));

		for (var c : classes) {

			if (indegree.get(c) == 0) {
				ready.add(c);
			}
		}

		List<Class<? extends MappedNamespaceElement<?, ?>>> result = new ArrayList<>();

		while (!ready.isEmpty()) {

			var next = ready.pollFirst();

			result.add(next);

			for (var neighbor : edges.getOrDefault(next, List.of())) {

				int degree = indegree.merge(neighbor, -1, Integer::sum);

				if (degree == 0) {
					ready.add(neighbor);
				}
			}
		}

		if (result.size() != classes.size()) {

			List<Class<? extends MappedNamespaceElement<?, ?>>> remaining = new ArrayList<>(classes);

			remaining.removeAll(result);

			remaining.sort(Comparator.comparing(Class::getName));

			result.addAll(remaining);
		}

		classes.clear();
		classes.addAll(result);
	}


	private Type getMappedLibraryType(Class<? extends MappedNamespaceElement<?, ?>> mappedClass) {
		MappedElementType annotation = mappedClass.getAnnotation(MappedElementType.class);

		if (annotation == null) {
			return null;
		}

		return utils.getLibTypeFromAnnotation(annotation.value());
	}

	@SuppressWarnings("unchecked")
	private Class<? extends MappedElement<?, ?>> castMappedElementClass(Class<?> mappedClass) {
		return (Class<? extends MappedElement<?, ?>>) mappedClass;
	}

	@SuppressWarnings("unchecked")
	private Class<? extends MappedNamespaceElement<?, ?>> castMappedNamespaceClass(Class<?> mappedClass) {
		return (Class<? extends MappedNamespaceElement<?, ?>>) mappedClass;
	}

	@SuppressWarnings("unchecked")
	private Class<? extends PackageElementType> castPackageClass(Class<?> mappedClass) {
		return (Class<? extends PackageElementType>) mappedClass;
	}

	@SuppressWarnings("unchecked")
	private Constructor<? extends MappedElement<?, ?>> castMappedConstructor(Constructor<?> constructor) {
		return constructor == null ? null : (Constructor<? extends MappedElement<?, ?>>) constructor;
	}

	@SuppressWarnings("unchecked")
	private Constructor<? extends MappedNamespaceElement<?, ?>> castNamespaceConstructor(Constructor<?> constructor) {
		return constructor == null ? null : (Constructor<? extends MappedNamespaceElement<?, ?>>) constructor;
	}

	private String safeName(Element element) {
		return element.getName() == null ? "<unnamed>" : element.getName();
	}
}