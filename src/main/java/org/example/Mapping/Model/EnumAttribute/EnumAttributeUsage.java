package org.example.Mapping.Model.EnumAttribute;


import Mapper.Mapper;
import Model.EmptyCore;
import Model.Usage;
import lombok.Getter;
import org.example.Mapping.TwinEnum;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.util.Arrays;
import java.util.Optional;

/** Attribute typed by a library enum; the value is the referenced enum literal. */
public abstract class EnumAttributeUsage<E extends Enum<E> & TwinEnum> extends Usage<EmptyCore, Feature, EnumDefinition> {

	@Getter private Optional<E> value = Optional.empty();

	protected EnumAttributeUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	protected abstract Class<E> getEnumClass();

	@Override
	public void fillSlots() {
		super.fillSlots();
		Optional<FeatureReferenceExpression> expression = sysmlElement.getOwnedElement().stream()
				.filter(FeatureReferenceExpression.class::isInstance).map(FeatureReferenceExpression.class::cast).findFirst();
		if (expression.isEmpty()) return;

		String symbol = expression.get().getReferent().getName();
		if (symbol == null) {
			throw new IllegalStateException("Enum attribute '%s' references an unnamed enum value.".formatted(getName()));
		}
		value = Optional.of(Arrays.stream(getEnumClass().getEnumConstants())
				.filter(e -> e.getStringRepresentation().equals(symbol)).findFirst()
				.orElseThrow(() -> new IllegalStateException("'%s' is not a valid value of %s"
						.formatted(symbol, getEnumClass().getSimpleName()))));
	}
}
