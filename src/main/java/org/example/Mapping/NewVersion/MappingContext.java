package org.example.Mapping.NewVersion;

import org.example.Containers.ContainerManager;
import org.example.Mapping.Interfaces.TwinEnum;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.Package;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.util.*;

public final class MappingContext {

	private final Utils utils;
	private final ContainerManager containerManager;

	private final Map<Element, MappedNamespaceElement<?>> mappedElements = new IdentityHashMap<>();

	public MappingContext(Utils utils, ContainerManager containerManager) {
		this.utils = utils;
		this.containerManager = containerManager;

	}


	public List<MappedNamespaceElement<?>> parseAll() throws MappingException {
		Collection<Package> elements = utils.collect(Package.class);

		parseAllPackages(elements);

		return new ArrayList<>(mappedElements.values());
	}

	public List<MappedNamespaceElement<?>> parseAllPackages(Collection<Package> elements) throws MappingException {
		List<MappedNamespaceElement<?>> roots = new ArrayList<>();

		for (Package element : elements) {

			MappedNamespaceElement<?> mapped = map(element, null);

			roots.add(mapped);
		}
		return roots;
	}

	public MappedNamespaceElement<?> map(Element element, MappedNamespaceElement<?> owner) throws MappingException {

		Objects.requireNonNull(element, "element");

		MappedNamespaceElement<?> existing = mappedElements.get(element);

		if (existing != null) {
			assignOwner(existing, owner);
			return existing;
		}

		MappedNamespaceElement<?> created = create(element);
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

	private MappedNamespaceElement<?> create(Element element) throws MappingException {
		Constructor<? extends MappedNamespaceElement<?>> constructor = containerManager.getMappedConstructor(element);
		constructor.setAccessible(true);

		try {
			return constructor.newInstance(element);
		} catch (Exception e) {
			throw new MappingException("Failed to create mapped element for '%s' using constructor '%s'.".formatted(element.getName(), constructor.toString()));
		}
	}


	private void assignOwner(MappedNamespaceElement<?> mapped, MappedNamespaceElement<?> owner) {
		if (owner == null) {
			return;
		}

		if (mapped.getOwner() == null) {
			mapped.setOwner(owner);
		}
	}

	public <T> List<T> mapSlot(MappedElement<?> mappedParent, String slotName, Class<T> expectedClass) throws MappingException {

		List<T> result = new ArrayList<>();

		for (Feature feature : mappedParent.getSysmlElement().getFeature()) {

			if (utils.isFromStandardOrDTLibrary(feature)) {
				continue;
			}

			if (!utils.redefinesOrSubsets(feature, slotName)) {
				continue;
			}

			MappedNamespaceElement<?> mapped = map(feature, mappedParent);

			if (!expectedClass.isInstance(mapped)) {
				throw new MappingException("Slot '%s': Element '%s' was mapped as '%s', expected '%s'.".formatted(slotName, feature.getName(), mapped.getClass().getSimpleName(), expectedClass.getSimpleName()));
			}

			result.add(expectedClass.cast(mapped));
		}


		return result;
	}

	public TwinAttributeUsageLoopVariableMapped mapLoopVariable(Usage element, MappedNamespaceElement<?> owner) throws MappingException {

		MappedNamespaceElement<?> existing = mappedElements.get(element);

		if (existing != null) {
			if (!(existing instanceof TwinAttributeUsageLoopVariableMapped loopVariable)) {
				throw new MappingException("Loop variable '%s' was already mapped as '%s'.".formatted(element.getName(), existing.getClass().getSimpleName()));
			}

			assignOwner(loopVariable, owner);
			return loopVariable;
		}

		TwinAttributeUsageLoopVariableMapped created = new TwinAttributeUsageLoopVariableMapped(element);

		created.setOwner(owner);
		mappedElements.put(element, created);

		try {
			created.parse(this);
			return created;
		} catch (MappingException | RuntimeException e) {
			mappedElements.remove(element);
			throw e;
		}
	}

	public <T extends MappedNamespaceElement<?>> T map(Element element, MappedNamespaceElement<?> owner, Class<T> expectedClass) throws MappingException {

		MappedNamespaceElement<?> mapped = map(element, owner);

		if (!expectedClass.isInstance(mapped)) {
			throw new MappingException("Element '%s' was mapped as '%s', but '%s' was expected.".formatted(element.getName(), mapped.getClass().getSimpleName(), expectedClass.getSimpleName()));
		}

		return expectedClass.cast(mapped);
	}

	public <S extends Element, T> List<T> mapOwned(MappedElement<?> mappedOwner, Class<S> sysmlMetaclass, Class<T> expectedClass) throws MappingException {

		List<T> result = new ArrayList<>();

		for (Element member : mappedOwner.getSysmlElement().getOwnedMember()) {

			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}


			S typedMember = sysmlMetaclass.cast(member);

			MappedNamespaceElement<?> mapped = map(typedMember, mappedOwner);

			if (expectedClass.isInstance(mapped)) {
				result.add(expectedClass.cast(mapped));
			}
		}

		return result;
	}

	public <S extends Element, T> List<T> mapOwnedNamespace(MappedNamespaceElement<?> mappedOwner, Class<S> sysmlMetaclass, Class<T> expectedClass) throws MappingException {

		List<T> result = new ArrayList<>();

		for (Element member : mappedOwner.getSysmlElement().getOwnedElement()) {

			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}


			S typedMember = sysmlMetaclass.cast(member);

			MappedNamespaceElement<?> mapped = map(typedMember, mappedOwner);

			if (expectedClass.isInstance(mapped)) {
				result.add(expectedClass.cast(mapped));
			}
		}

		return result;
	}



	public <T extends MappedNamespaceElement<?>> MappedReference<T> mapReference(Element referent, Class<T> expectedClass) throws MappingException {

		Objects.requireNonNull(referent, "referent");
		Objects.requireNonNull(expectedClass, "expectedClass");

		T mapped = map(referent, null, expectedClass);

		return new MappedReference<>(mapped);
	}

	public <T extends Enum<T> & TwinEnum> T extractEnum(
			TwinAttributeUsageMapped attribute,
			Class<T> enumClass
	) throws MappingException {

		var expression = attribute.getSysmlElement()
				.getOwnedElement()
				.stream()
				.filter(FeatureReferenceExpression.class::isInstance)
				.map(FeatureReferenceExpression.class::cast)
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"Attribute '%s' has no enum reference"
										.formatted(attribute.getName())
						)
				);

		var referent = expression.getReferent();

		String symbol = referent.getName();

		for (T value : enumClass.getEnumConstants()) {
			if (value.getStringRepresentation().equals(symbol)) {
				return value;
			}
		}

		throw new MappingException(
				"Unknown value '%s' for enum '%s'"
						.formatted(symbol, enumClass.getSimpleName())
		);
	}


	public Utils getUtils() {
		return utils;
	}

}
