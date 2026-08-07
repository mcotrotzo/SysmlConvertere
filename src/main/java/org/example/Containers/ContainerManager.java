package org.example.Containers;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NoMappedElementException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

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

	public List<Class<? extends MappedElement<?>>> getLibraryMappedClasses() {
		return getMappedClasses(MappedElementType.class);
	}

	public List<Class<? extends MappedElement<?>>> getMetaclassMappedClasses() {
		return getMappedClasses(MappedMetaclass.class);
	}

	private List<Class<? extends MappedElement<?>>> getMappedClasses(Class<? extends Annotation> annotation) {
		return containers.getOrDefault(annotation.getName(), Collections.emptyList()).stream().filter(MappedElement.class::isAssignableFrom).filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).map(this::castMappedClass).collect(Collectors.toCollection(ArrayList::new));
	}


	private void scan() {
		try (ScanResult scanResult = new ClassGraph().acceptPackages("org.example").enableAllInfo().scan()) {

			for (var annotationInfo : scanResult.getAllAnnotations()) {
				String annotationName = annotationInfo.getName();

				for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(annotationName)) {

					if (classInfo.isInterface() || classInfo.isAbstract()) {
						continue;
					}

					containers.computeIfAbsent(annotationName, ignored -> new ArrayList<>()).add(classInfo.loadClass());
				}
			}
		}
	}


	public List<Class<? extends MappedElement<?>>> getMappedElementClasses() {
		List<Class<? extends MappedElement<?>>> result = containers.getOrDefault(MappedElementType.class.getName(), Collections.emptyList()).stream().filter(MappedElement.class::isAssignableFrom).filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).map(this::castMappedClass).collect(Collectors.toCollection(ArrayList::new));

		sortBySysmlTypeSpecificity(result);

		return result;
	}

	private boolean isLibraryTypeCompatible(Type sysmlElement, Class<? extends MappedElement<?>> mappedClass) {
		Type mappedLibraryType = getMappedLibraryType(mappedClass);

		return mappedLibraryType != null && TypeUtil.isCompatible(sysmlElement, mappedLibraryType);
	}

	private Constructor<?> findCompatibleConstructor(Class<? extends MappedElement<?>> mappedClass, Type sysmlElement) {
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


	public Constructor<? extends MappedElement<?>> getMappedConstructor(Type sysmlElement) throws MappingException {

		Objects.requireNonNull(sysmlElement, "sysmlElement");


		if (!(sysmlElement instanceof org.omg.sysml.lang.sysml.InvocationExpression)) {
			Constructor<? extends MappedElement<?>> libraryConstructor = findLibraryConstructor(sysmlElement);
			if (libraryConstructor != null) {
				return libraryConstructor;
			}
		}

		Constructor<? extends MappedElement<?>> metaclassConstructor = findMetaclassConstructor(sysmlElement);
		if (metaclassConstructor != null) {
			return metaclassConstructor;
		}

		if (sysmlElement instanceof org.omg.sysml.lang.sysml.InvocationExpression) {
			Constructor<? extends MappedElement<?>> libraryConstructor = findLibraryConstructor(sysmlElement);
			if (libraryConstructor != null) {
				return libraryConstructor;
			}
		}


		throw new NoMappedElementException("No mapped constructor found for '%s' (%s).".formatted(safeName(sysmlElement), sysmlElement.getClass().getSimpleName()));
	}

	private Constructor<? extends MappedElement<?>> findMetaclassConstructor(Type sysmlElement) {
		Constructor<?> best = null;

		for (Class<? extends MappedElement<?>> mappedClass : getMetaclassMappedClasses()) {

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

		return castConstructor(best);
	}

	private Constructor<? extends MappedElement<?>> findLibraryConstructor(Type sysmlElement)
			throws MappingException {

		List<Class<? extends MappedElement<?>>> classes = getLibraryMappedClasses();
		sortBySysmlTypeSpecificity(classes);

		Map<Constructor<?>, Type> candidates = new LinkedHashMap<>();

		for (Class<? extends MappedElement<?>> mappedClass : classes) {
			if (!isLibraryTypeCompatible(sysmlElement, mappedClass)) {
				continue;
			}

			Constructor<?> constructor = findCompatibleConstructor(mappedClass, sysmlElement);

			if (constructor != null) {
				candidates.put(constructor, getMappedLibraryType(mappedClass));
			}
		}

		candidates.entrySet().removeIf(candidate ->
				candidates.entrySet().stream().anyMatch(other ->
						other != candidate
								&& TypeUtil.specializes(
								other.getValue(),
								candidate.getValue()
						)
				)
		);

		if (candidates.size() > 1) {
			throw new MappingException(
					"Multiple constructors found for '%s' (%s). Available types: %s"
							.formatted(
									safeName(sysmlElement),
									sysmlElement.getClass().getSimpleName(),
									candidates.values().stream()
											.map(Type::getQualifiedName)
											.collect(Collectors.joining(", "))
							)
			);
		}

		return candidates.keySet().stream()
				.findFirst()
				.map(this::castConstructor)
				.orElse(null);
	}

	@SuppressWarnings("unchecked")
	private Constructor<? extends MappedElement<?>> castConstructor(Constructor<?> constructor) {
		return constructor == null ? null : (Constructor<? extends MappedElement<?>>) constructor;
	}


	private void sortBySysmlTypeSpecificity(List<Class<? extends MappedElement<?>>> classes) {
		Map<Class<? extends MappedElement<?>>, Type> libraryTypes = new HashMap<>();
		for (var mappedClass : classes) {
			libraryTypes.put(mappedClass, getMappedLibraryType(mappedClass));
		}

		Map<Class<? extends MappedElement<?>>, List<Class<? extends MappedElement<?>>>> edges = new HashMap<>();
		Map<Class<? extends MappedElement<?>>, Integer> indegree = new HashMap<>();
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
			if (libraryTypes.get(c) == null) {
				for (var other : classes) {
					if (other != c && libraryTypes.get(other) != null) {
						edges.computeIfAbsent(other, k -> new ArrayList<>()).add(c);
						indegree.merge(c, 1, Integer::sum);
					}
				}
			}
		}

		TreeSet<Class<? extends MappedElement<?>>> ready = new TreeSet<>(Comparator.comparing(Class::getName));
		for (var c : classes) {
			if (indegree.get(c) == 0) {
				ready.add(c);
			}
		}

		List<Class<? extends MappedElement<?>>> result = new ArrayList<>();
		while (!ready.isEmpty()) {
			var next = ready.pollFirst();
			result.add(next);
			for (var neighbor : edges.getOrDefault(next, List.of())) {
				int deg = indegree.merge(neighbor, -1, Integer::sum);
				if (deg == 0) {
					ready.add(neighbor);
				}
			}
		}

		if (result.size() != classes.size()) {
			List<Class<? extends MappedElement<?>>> remaining = new ArrayList<>(classes);
			remaining.removeAll(result);
			remaining.sort(Comparator.comparing(Class::getName));
			result.addAll(remaining);
		}

		classes.clear();
		classes.addAll(result);
	}

	private Type getMappedLibraryType(Class<? extends MappedElement<?>> mappedClass) {
		MappedElementType annotation = mappedClass.getAnnotation(MappedElementType.class);

		if (annotation == null) {
			return null;
		}

		return utils.getLibTypeFromAnnotation(annotation.value());
	}


	@SuppressWarnings("unchecked")
	private Class<? extends MappedElement<?>> castMappedClass(Class<?> mappedClass) {
		return (Class<? extends MappedElement<?>>) mappedClass;
	}

	private String safeName(Type element) {
		return element.getName() == null ? "<unnamed>" : element.getName();
	}
}