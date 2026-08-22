package org.example.Mapping.NewVersion;

import lombok.Getter;
import org.example.Mapping.Interfaces.TwinEnum;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.util.Arrays;


public abstract class EnumAttribute<E extends Enum<E> & TwinEnum>
		extends MappedElement<Feature> {


	@Getter
	private E value;

	protected EnumAttribute(Feature sysmlElement) {
		super(sysmlElement);
	}

	protected abstract Class<E> getEnumClass();

	@Override
	public void parse(MappingContext context) throws MappingException {

		FeatureReferenceExpression expression =
				getSysmlElement()
						.getOwnedElement()
						.stream()
						.filter(FeatureReferenceExpression.class::isInstance)
						.map(FeatureReferenceExpression.class::cast)
						.findFirst()
						.orElseThrow(() ->
								new MappingException(
										"Enum attribute '%s' has no enum reference."
												.formatted(getName())
								)
						);

		Feature referent = expression.getReferent();

		String symbol = referent.getName();

		if (symbol == null) {
			throw new MappingException(
					"Enum attribute '%s' references an unnamed enum value."
							.formatted(getName())
			);
		}
		value = Arrays.stream(getEnumClass().getEnumConstants())
				.peek(x -> System.out.println("Checking enum constant: " + x.getStringRepresentation()))
				.filter(e -> e.getStringRepresentation().equals(symbol))
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"'%s' is not a valid value of %s"
										.formatted(
												symbol,
												getEnumClass().getSimpleName()
										)
						)
				);
	}

}
