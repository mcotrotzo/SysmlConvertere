package org.example.Mapping.NewVersion;

import lombok.Getter;
import org.example.Containers.ContainerManager;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TwinDefLibrary;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Base.UserLibrary;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinEnumPackage.TwinEnum;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class MappingContext {

	@Getter
	private final Utils utils;
	private final ContainerManager containerManager;

	private final Map<Element, MappedNamespaceElement<?, ?>> mappedElements = new IdentityHashMap<>();

	private final Map<String, CompartmentMapped<?>> mappedCompartments
			= new HashMap<>();

	private void registerCompartment(CompartmentMapped<?> compartment) {
		CompartmentMapped<?> previous =
				mappedCompartments.putIfAbsent(
						compartment.getId(),
						compartment
				);

		if (previous != null && previous != compartment) {
			throw new IllegalStateException(
					"Duplicate compartment id: " + compartment.getId()
			);
		}
	}

	public Collection<CompartmentMapped<?>> getMappedCompartments() {
		return List.copyOf(mappedCompartments.values());
	}


	public MappingContext(Utils utils, ContainerManager containerManager) {
		this.utils = utils;
		this.containerManager = containerManager;

	}


	public List<MappedNamespaceElement<?, ?>> parseAll() throws MappingException {
		Collection<Package> elements = utils.collect(Package.class);

		parseAllPackages(elements);
		resolveParents();
		resolveRoles();
		postParse();
		validateAll();

		return new ArrayList<>(mappedElements.values());
	}

	private void resolveRoles() throws MappingException {
		for (var mapped : mappedElements.values()) {
			mapped.resolveRoles(this);
		}
	}
	private void postParse() {
		for (MappedNamespaceElement<?, ?> mapped : mappedElements.values()) {
			mapped.postParse(this);
		}
	}

	private void validateAll() throws MappingException {

		for (MappedNamespaceElement<?, ?> mapped : mappedElements.values()) {
			mapped.postValidate();
		}
	}

	public List<MappedNamespaceElement<?, ?>> parseAllPackages(Collection<Package> elements) throws MappingException {
		List<MappedNamespaceElement<?, ?>> roots = new ArrayList<>();

		for (Package element : elements) {

			MappedNamespaceElement<?, ?> mapped = map(element, null);

			roots.add(mapped);
		}
		return roots;
	}

	public MappedNamespaceElement<?, ?> map(Element element, MappedNamespaceElement<?, ?> owner) throws MappingException {

		Objects.requireNonNull(element, "element");


		MappedNamespaceElement<?, ?> existing = mappedElements.get(element);
		if (existing != null) {
			assignOwner(existing, owner);
			return existing;
		}



		MappedNamespaceElement<?, ?> created = create(element);
		created.setOwner(owner);

		mappedElements.put(element, created);

		try {
			created.parse(this);
			return created;
		} catch (MappingException | RuntimeException exception) {
			mappedElements.remove(element);
			throw exception;
		}
	}

	private MappedNamespaceElement<?, ?> create(Element element) throws MappingException {
		Constructor<? extends MappedNamespaceElement<?, ?>> constructor = containerManager.getMappedConstructor(element);
		constructor.setAccessible(true);

		try {
			return constructor.newInstance(element);
		} catch (Exception e) {
			throw new MappingException("Failed to create mapped element for '%s' using constructor '%s'.".formatted(element.getName(), constructor.toString()));
		}
	}


	private void assignOwner(MappedNamespaceElement<?, ?> mapped, MappedNamespaceElement<?, ?> owner) {

		if (owner == null) {
			return;
		}

		if (mapped.getOwner() == null) {
			mapped.setOwner(owner);
		}
	}

	public <T extends MappedElement<?, Usage>>
	CompartmentMapped<T> mapCompartment(
			Model<?> parent,
			T element,
			boolean inherited,
			String slotName
	) {
		CompartmentMapped<T> compartment =
				new CompartmentMapped<>(
						parent,
						element,
						inherited
				);

		registerCompartment(compartment);

		return compartment;
	}

	@SuppressWarnings("unchecked")
	public <T extends MappedElement<?, Usage>>
	CompartmentMapped<T> getCompartment(
			Model<?> parent,
			T element
	) {
		String path =
				parent.path()
						+ ":compartment:"
						+ element.path();

		String id = UUID.nameUUIDFromBytes(
				path.getBytes(StandardCharsets.UTF_8)
		).toString();

		CompartmentMapped<?> compartment =
				mappedCompartments.get(id);

		if (compartment == null) {
			throw new IllegalStateException(
					"No compartment for parent '%s' and element '%s'"
							.formatted(
									parent.path(),
									element.path()
							)
			);
		}

		return (CompartmentMapped<T>) compartment;
	}

	public <T extends MappedElement<?,Usage>> CompartmentContainerMapped<T> mapPrivateSlot(
			MappedElement<?, ?> mappedParent,
			String slotName,
			Class<T> expectedClass
	) throws MappingException {

		CompartmentContainerMapped<T> result = new CompartmentContainerMapped<>();
		List<T> inherited = new ArrayList<>();
		List<T> owned = new ArrayList<>();

		List<Type> typesToSearch = new ArrayList<>();

		typesToSearch.add(mappedParent.getSysmlElement());

		if (mappedParent.getSysmlElement() instanceof Feature feature) {
			typesToSearch.addAll(feature.getType());
		}

		for (Type type : new ArrayList<>(typesToSearch)) {
			typesToSearch.addAll(TypeUtil.getSupertypesOf(type, true));
		}

		for (Type type : typesToSearch) {
			for (Element element : type.getOwnedElement()) {
				if (!(element instanceof Feature feature)) continue;

				if (!utils.redefinesOrSubsets(feature, slotName)
						&& !slotName.equals(feature.getName())) {
					continue;
				}

				MappedNamespaceElement<?, ?> mapped = map(feature, mappedParent);

				if (!expectedClass.isInstance(mapped)) {
					throw new MappingException(
							"Slot '%s': Element '%s' was mapped as '%s', expected '%s'."
									.formatted(
											slotName,
											feature.getName(),
											mapped.getClass().getSimpleName(),
											expectedClass.getSimpleName()
									)
					);
				}

				T typed = expectedClass.cast(mapped);

				if (feature.getOwner() == mappedParent.getSysmlElement()) {
					owned.add(typed);
				} else {
					inherited.add(typed);
				}
			}
		}

		for (T inheritedElement : inherited) {
			CompartmentMapped<T> compartment =
					new CompartmentMapped<>(
							mappedParent,
							inheritedElement,
							true
					);

			registerCompartment(compartment);
			result.addCompartment(compartment);
		}

		for (T ownedElement : owned) {
			CompartmentMapped<T> compartment =
					new CompartmentMapped<>(
							mappedParent,
							ownedElement,
							false

					);

			registerCompartment(compartment);
			result.addCompartment(compartment);
		}


		return result;
	}

	private void resolveParents() {
		for (MappedNamespaceElement<?, ?> mapped : mappedElements.values()) {

			Element sysmlOwner = mapped.getSysmlElement().getOwner();
			MappedNamespaceElement<?, ?> mappedOwner = null;

			while (sysmlOwner != null) {
				mappedOwner = mappedElements.get(sysmlOwner);
				if (mappedOwner != null) {
					break;
				}
				sysmlOwner = sysmlOwner.getOwner();
			}

			mapped.setOwner(mappedOwner);
		}
	}


	public <T extends MappedElement<?, Usage>>
	CompartmentContainerMapped<T> mapSlot(
			MappedElement<?, ?> mappedParent,
			String slotName,
			Class<T> expectedClass
	) throws MappingException {

		boolean parentIsFromDTLibrary =
				utils.isFromDTLibrary(mappedParent.getSysmlElement());

		List<T> inherited = new ArrayList<>();
		List<T> owned = new ArrayList<>();

		CompartmentContainerMapped<T> result =
				new CompartmentContainerMapped<>();

		for (Feature feature : mappedParent.getSysmlElement().getFeature()) {
			if (utils.isFromStandardLibrary(feature)) {
				continue;
			}

			if (!parentIsFromDTLibrary && utils.isFromDTLibrary(feature)) {
				continue;
			}

			if (!utils.redefinesOrSubsets(feature, slotName)) {
				continue;
			}

			MappedNamespaceElement<?, ?> mapped = map(feature, null);

			if (!expectedClass.isInstance(mapped)) {
				throw new MappingException(
						"Slot '%s': Element '%s' was mapped as '%s', expected '%s'."
								.formatted(
										slotName,
										feature.getName(),
										mapped.getClass().getSimpleName(),
										expectedClass.getSimpleName()
								)
				);
			}

			T typed = expectedClass.cast(mapped);

			if (feature.getOwner() == mappedParent.getSysmlElement()) {
				owned.add(typed);
			} else {
				inherited.add(typed);
			}
		}

		for (T inheritedElement : inherited) {
			CompartmentMapped<T> compartment =
					new CompartmentMapped<>(
							mappedParent,
							inheritedElement,
							true
					);

			registerCompartment(compartment);
			result.addCompartment(compartment);
		}

		for (T ownedElement : owned) {
			CompartmentMapped<T> compartment =
					new CompartmentMapped<>(
							mappedParent,
							ownedElement,
							false
					);

			registerCompartment(compartment);
			result.addCompartment(compartment);
		}
		return result;
	}

	public <T extends MappedNamespaceElement<?, ?>> T map(Element element, MappedNamespaceElement<?, ?> owner, Class<T> expectedClass) throws MappingException {

		MappedNamespaceElement<?, ?> mapped = map(element, owner);


		if (!expectedClass.isInstance(mapped)) {
			throw new MappingException("Element '%s' was mapped as '%s', but '%s' was expected. Parent %s".formatted(element.getName(), mapped.getClass().getSimpleName(), expectedClass.getSimpleName(), element.path()));
		}

		return expectedClass.cast(mapped);
	}

	public <S extends Element, T> List<T> mapOwned(MappedElement<?, ?> mappedOwner, Class<S> sysmlMetaclass, Class<T> expectedClass) throws MappingException {
		List<T> result = new ArrayList<>();

		for (Element member : mappedOwner.getSysmlElement().getOwnedMember()) {

			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}


			S typedMember = sysmlMetaclass.cast(member);

			MappedNamespaceElement<?, ?> mapped = map(typedMember, mappedOwner);

			if (expectedClass.isInstance(mapped)) {
				result.add(expectedClass.cast(mapped));
			}
		}

		return result;
	}

	public <S extends Element, T> List<T> mapOwnedNamespace(MappedNamespaceElement<?, ?> mappedOwner, Class<S> sysmlMetaclass, Class<T> expectedClass) throws MappingException {

		List<T> result = new ArrayList<>();

		for (Element member : mappedOwner.getSysmlElement().getOwnedElement()) {

			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}


			S typedMember = sysmlMetaclass.cast(member);

			MappedNamespaceElement<?, ?> mapped = map(typedMember, mappedOwner);

			if (expectedClass.isInstance(mapped)) {
				result.add(expectedClass.cast(mapped));
			}
		}

		return result;
	}
	@SuppressWarnings("unchecked")
	public <T extends MappedElement<?, Usage>>
	CompartmentMapped<T> getOwnCompartment(T element) {

		List<CompartmentMapped<?>> matches =
				mappedCompartments.values().stream()
						.filter(c -> c.getElement() == element)
						.filter(c -> !c.isInherited())
						.toList();

		if (matches.size() != 1) {
			throw new IllegalStateException(
					"Expected exactly one non-inherited compartment for '%s', found %d"
							.formatted(element.path(), matches.size())
			);
		}

		return (CompartmentMapped<T>) matches.getFirst();
	}

	public <T extends MappedNamespaceElement<?, ?>> MappedReference<T> mapReference(Element referent, Class<T> expectedClass) throws MappingException {

		Objects.requireNonNull(referent, "referent");
		Objects.requireNonNull(expectedClass, "expectedClass");

		T mapped = map(referent, null, expectedClass);

		return new MappedReference<>(mapped);
	}

	public <T extends Enum<T> & TwinEnum> T extractEnum(TwinAttributeMapped<Usage> attribute, Class<T> enumClass) throws MappingException {

		var expression = attribute.getSysmlElement().getOwnedElement().stream().filter(FeatureReferenceExpression.class::isInstance).map(FeatureReferenceExpression.class::cast).findFirst().orElseThrow(() -> new MappingException("Attribute '%s' has no enum reference".formatted(attribute.getName())));

		var referent = expression.getReferent();

		String symbol = referent.getName();

		for (T value : enumClass.getEnumConstants()) {
			if (value.getStringRepresentation().equals(symbol)) {
				return value;
			}
		}

		throw new MappingException("Unknown value '%s' for enum '%s'".formatted(symbol, enumClass.getSimpleName()));
	}


	public <T extends MappedNamespaceElement<?, ?>>
	MappedReference<T> tryMapReference(
			Element referent,
			Class<T> expectedClass
	) throws MappingException {

		Objects.requireNonNull(referent, "referent");
		Objects.requireNonNull(expectedClass, "expectedClass");

		MappedNamespaceElement<?, ?> mapped =
				map(referent, null);

		if (!expectedClass.isInstance(mapped)) {
			return null;
		}

		return new MappedReference<>(
				expectedClass.cast(mapped)
		);
	}



	public CompartmentMapped<? extends MappedElement<?, Usage>>
	resolveCompartmentChain(
			List<? extends MappedElement<?, Usage>> chain
	) {
		if (chain.size() < 2) {
			throw new IllegalStateException(
					"Feature chain must contain at least two elements"
			);
		}

		CompartmentMapped<? extends MappedElement<?, Usage>> current = null;

		for (int i = 1; i < chain.size(); i++) {
			MappedElement<?, Usage> parent = chain.get(i - 1);
			MappedElement<?, Usage> element = chain.get(i);

			current = getCompartment(parent, element);
		}

		return current;
	}

}
