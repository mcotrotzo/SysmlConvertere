package org.example.Mapping.TwinAttributeMapped;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinEnumPackage.TwinEnum;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.util.Arrays;
import java.util.Optional;

public abstract class EnumAttribute<E extends Enum<E> & TwinEnum> extends MappedElement<Feature, Usage> implements org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute<E> {

	private Optional<E> value = Optional.empty();

	protected EnumAttribute(Feature sysmlElement) {
		super(sysmlElement);
	}

	protected abstract Class<E> getEnumClass();

	@Override
	public void parse(MappingContext context) throws MappingException {

		Optional<FeatureReferenceExpression> expression = getSysmlElement().getOwnedElement().stream().filter(FeatureReferenceExpression.class::isInstance).map(FeatureReferenceExpression.class::cast).findFirst();

		if (expression.isEmpty()) {
			return;
		}

		Feature referent = expression.get().getReferent();
		String symbol = referent.getName();

		if (symbol == null) {
			throw new MappingException("Enum attribute '%s' references an unnamed enum value.".formatted(getName()));
		}

		E resolved = Arrays.stream(getEnumClass().getEnumConstants()).filter(e -> e.getStringRepresentation().equals(symbol)).findFirst().orElseThrow(() -> new MappingException("'%s' is not a valid value of %s".formatted(symbol, getEnumClass().getSimpleName())));

		value = Optional.of(resolved);
	}

	public Optional<E> getTwinEnum() {
		return value;
	}
}