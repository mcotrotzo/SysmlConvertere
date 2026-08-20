package org.example.Mapping.NewVersion;

import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedMetaclass
public abstract class AbstractMappedQuery
		extends FunctionMapped<Function>
		implements Query {

	protected TwinAttributeMapped twinAttribute;

	protected Optional<TwinIntegerMapped> since = Optional.empty();
	protected Optional<EnumTimeUnitMapped> sinceUnit = Optional.empty();
	protected Optional<EnumOrderByMapped> orderBy = Optional.empty();
	protected Optional<TwinIntegerMapped> limit = Optional.empty();
	protected Optional<TwinBooleanMapped> filterExpression = Optional.empty();

	protected TwinAttributeMapped result;

	public AbstractMappedQuery(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		twinAttribute = parseRequiredSlot(
				context,
				"twinAttribute",
				TwinAttributeMapped.class
		);

		since = parseOptionalSlot(
				context,
				"since",
				TwinIntegerMapped.class
		);

		sinceUnit = parseOptionalSlot(
				context,
				"sinceUnit",
				EnumTimeUnitMapped.class
		);

		orderBy = parseOptionalSlot(
				context,
				"orderBy",
				EnumOrderByMapped.class
		);

		limit = parseOptionalSlot(
				context,
				"limit",
				TwinIntegerMapped.class
		);


		filterExpression = parseOptionalSlot(
				context,
				"filterExpression",
				TwinBooleanMapped.class
		);

		result = parseRequiredSlot(
				context,
				"result",
				TwinAttributeMapped.class
		);

		validateMulti(
				twinAttribute,
				"twinAttribute",1,1
		);
		validateMulti(
				result,
				"result",0,-1
		);
	}


	protected <T extends MappedElement<?>> T parseRequiredSlot(
			MappingContext context,
			String slotName,
			Class<T> clazz
	) throws MappingException {

		List<T> values =
				context.mapSlot(
						this,
						slotName,
						clazz
				);

		if (values.size() != 1) {
			throw new MappingException(
					"%s '%s': slot '%s' must be specified exactly once, but found %d."
							.formatted(
									getClass().getSimpleName(),
									getName(),
									slotName,
									values.size()
							)
			);
		}

		return values.getFirst();
	}

	protected <T extends MappedElement<?>> Optional<T> parseOptionalSlot(
			MappingContext context,
			String slotName,
			Class<T> clazz
	) throws MappingException {

		List<T> values =
				context.mapSlot(
						this,
						slotName,
						clazz
				);

		if (values.size() > 1) {
			throw new MappingException(
					"%s '%s': slot '%s' may be specified at most once, but found %d."
							.formatted(
									getClass().getSimpleName(),
									getName(),
									slotName,
									values.size()
							)
			);
		}

		return values.stream().findFirst();
	}

	protected void validateMulti(
			TwinAttributeMapped attribute,
			String slotName,
			int requiredLower,
			int requiredUpper
	) throws MappingException {

		ElemWithMult multiplicity =
				Utils.getMultiplicityRange(
						attribute.getSysmlElement()
				);

		if (multiplicity.getLowerBound() != requiredLower
				|| multiplicity.getUpperBound() != requiredUpper) {

			throw new MappingException(
					"%s '%s': slot '%s' must have multiplicity [%d..%s], but found [%d..%s]."
							.formatted(
									getClass().getSimpleName(),
									getName(),
									slotName,
									requiredLower,
									requiredUpper == -1 ? "*" : requiredUpper,
									multiplicity.getLowerBound(),
									multiplicity.getUpperBound() == -1
											? "*"
											: multiplicity.getUpperBound()
							)
			);
		}
	}

	@Override
	public TwinAttribute getTwinAttribute() {
		return twinAttribute;
	}

	@Override
	public Optional<TwinIntegerAttribute> getSince() {
		return since.map(x -> x);
	}

	@Override
	public Optional<EnumTimeUnit> getSinceUnit() {
		return sinceUnit.map(EnumTimeUnitMapped::getValue);
	}

	@Override
	public Optional<EnumOrderBy> getOrderBy() {
		return orderBy.map(EnumOrderByMapped::getValue);
	}

	@Override
	public Optional<TwinIntegerAttribute> getLimit() {
		return limit.map(x -> x);
	}

	@Override
	public Optional<TwinBooleanAttribute> getFilterExpression() {
		return filterExpression.map(x -> x);
	}
}