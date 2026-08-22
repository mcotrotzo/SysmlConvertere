package org.example.Mapping.NewVersion;

import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Function;

import java.util.List;
import java.util.Optional;

@MappedMetaclass
public abstract class AbstractMappedQuery
		extends FunctionMapped<Function>
		implements Query {

	protected TwinAttributeUsageMapped twinAttribute;

	protected Optional<TwinIntegerMappedUsage> since = Optional.empty();
	protected Optional<EnumTimeUnitMapped> sinceUnit = Optional.empty();
	protected Optional<EnumOrderByMapped> orderBy = Optional.empty();
	protected Optional<TwinIntegerMappedUsage> limit = Optional.empty();
	protected Optional<TwinBooleanMappedUsage> filterExpression = Optional.empty();

	protected TwinAttributeUsageMapped result;

	public AbstractMappedQuery(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		twinAttribute = parseRequiredSlot(
				context,
				"twinAttribute",
				TwinAttributeUsageMapped.class
		);

		since = parseOptionalSlot(
				context,
				"since",
				TwinIntegerMappedUsage.class
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
				TwinIntegerMappedUsage.class
		);


		filterExpression = parseOptionalSlot(
				context,
				"filterExpression",
				TwinBooleanMappedUsage.class
		);

		result = parseRequiredSlot(
				context,
				"result",
				TwinAttributeUsageMapped.class
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
			TwinAttributeUsageMapped attribute,
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
	public TwinAttributeUsage getTwinAttribute() {
		return twinAttribute;
	}

	@Override
	public Optional<TwinIntegerAttributeUsage> getSince() {
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
	public Optional<TwinIntegerAttributeUsage> getLimit() {
		return limit.map(x -> x);
	}

	@Override
	public Optional<TwinBooleanAttributeUsage> getFilterExpression() {
		return filterExpression.map(x -> x);
	}
}