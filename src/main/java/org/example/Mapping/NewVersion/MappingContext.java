package org.example.Mapping.NewVersion;

import org.example.Containers.ContainerManager;
import org.example.Mapping.Interfaces.FeatureReference;
import org.example.Mapping.Interfaces.TwinEnum;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.util.*;

public final class MappingContext {

	private final Utils utils;
	private final ContainerManager containerManager;

	private final Map<Type, MappedElement<?>> mappedElements = new IdentityHashMap<>();

	public MappingContext(Utils utils, ContainerManager containerManager) {
		this.utils = utils;
		this.containerManager = containerManager;

	}


	public List<MappedElement<?>> parseAll() throws MappingException {
		Collection<Type> elements = utils.collect(Type.class);

		parseAllDefinitions(elements);

		return new ArrayList<>(mappedElements.values());
	}

	public List<MappedElement<?>> parseAllDefinitions(Collection<? extends Type> elements) throws MappingException {
		List<MappedElement<?>> roots = new ArrayList<>();

		for (Type element : elements) {
			if (!isDefiniton(element)) {
				continue;
			}

			roots.add(map(element, null));

		}

		return roots;
	}

	public MappedElement<?> map(Type element, MappedElement<?> owner) throws MappingException {

		Objects.requireNonNull(element, "element");

		MappedElement<?> existing = mappedElements.get(element);

		if (existing != null) {
			assignOwner(existing, owner);
			return existing;
		}

		MappedElement<?> created = create(element);
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

	private boolean isDefiniton(Type element) {
		if (!(element instanceof Definition)) {
			return false;
		}

		return true;
	}

	private MappedElement<?> create(Type element) throws MappingException {
		Constructor<? extends MappedElement<?>> constructor = containerManager.getMappedConstructor(element);
		constructor.setAccessible(true);

		try {
			return constructor.newInstance(element);
		} catch (Exception e) {
			throw new MappingException("Failed to create mapped element for '%s' using constructor '%s'.".formatted(element.getName(), constructor.toString()));
		}
	}


	private void assignOwner(MappedElement<?> mapped, MappedElement<?> owner) {
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

			MappedElement<?> mapped = map(feature, mappedParent);

			if (!expectedClass.isInstance(mapped)) {
				throw new MappingException("Slot '%s': Element '%s' was mapped as '%s', expected '%s'.".formatted(slotName, feature.getName(), mapped.getClass().getSimpleName(), expectedClass.getSimpleName()));
			}

			result.add(expectedClass.cast(mapped));
		}

		return result;
	}
	public TwinAttributeLoopVariableMapped mapLoopVariable(
			Type element,
			MappedElement<?> owner
	) throws MappingException {

		MappedElement<?> existing = mappedElements.get(element);

		if (existing != null) {
			if (!(existing instanceof TwinAttributeLoopVariableMapped loopVariable)) {
				throw new MappingException(
						"Loop variable '%s' was already mapped as '%s'."
								.formatted(
										element.getName(),
										existing.getClass().getSimpleName()
								)
				);
			}

			assignOwner(loopVariable, owner);
			return loopVariable;
		}

		TwinAttributeLoopVariableMapped created =
				new TwinAttributeLoopVariableMapped(element);

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

	public <T extends MappedElement<?>> T map(Type element, MappedElement<?> owner, Class<T> expectedClass) throws MappingException {

		MappedElement<?> mapped = map(element, owner);

		if (!expectedClass.isInstance(mapped)) {
			throw new MappingException("Element '%s' was mapped as '%s', but '%s' was expected.".formatted(element.getName(), mapped.getClass().getSimpleName(), expectedClass.getSimpleName()));
		}

		return expectedClass.cast(mapped);
	}

	public <S extends Type, T> List<T> mapOwned(
			MappedElement<?> mappedOwner,
			Class<S> sysmlMetaclass,
			Class<T> expectedClass
	) throws MappingException {

		List<T> result = new ArrayList<>();

		for (Element member : mappedOwner.getSysmlElement().getOwnedMember()) {

			if (!sysmlMetaclass.isInstance(member)) {
				continue;
			}

			S typedMember = sysmlMetaclass.cast(member);

			MappedElement<?> mapped =
					map(typedMember, mappedOwner);

			if (expectedClass.isInstance(mapped)) {
				result.add(expectedClass.cast(mapped));
			}
		}

		return result;
	}


	public <T extends MappedElement<?>> MappedReference<T> mapReference(Type referent, Class<T> expectedClass) throws MappingException {

		Objects.requireNonNull(referent, "referent");
		Objects.requireNonNull(expectedClass, "expectedClass");

		T mapped = map(referent, null, expectedClass);

		return new MappedReference<>(mapped);
	}

	public <T extends Enum<T> & TwinEnum> T extractEnum(
			TwinAttributeMapped attribute,
			Class<T> enumClass
	) throws MappingException {

		var expression = attribute.getTwinExpressions()
				.orElseThrow(() -> new MappingException(
						"Attribute '%s' has no expression"
								.formatted(attribute.getName())
				));

		if (!(expression instanceof FeatureReference featureReference)) {
			throw new MappingException(
					"Attribute '%s' does not contain an enum reference"
							.formatted(attribute.getName())
			);
		}

		String symbol = featureReference
				.getTarget()
				.getReferent()
				.getName();

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
