package org.example.Mapping.NewVersion;

import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.PartUsage;
import org.omg.sysml.lang.sysml.Redefinition;

import java.util.*;

@MappedMetaclass
public abstract class AbstractMappedQuery extends MappedElement<PartUsage> implements Query {

	protected Set<TwinAttributeMapped> twinAttributes = new HashSet<>();
	protected Set<TwinIntegerMapped> since = new HashSet<>();
	protected EnumTimeUnit sinceUnit;
	protected EnumOrderBy orderBy;
	protected Set<TwinIntegerMapped> limit = new HashSet<>();
	protected Set<TwinBooleanMapped> filterExpression = new HashSet<>();
	protected Set<TwinAttributeMapped> result = new HashSet<>();

	public AbstractMappedQuery(PartUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		validateOnlyRedefinitions(context);

		twinAttributes = parseSlot(context, "twinAttribute", TwinAttributeMapped.class);

		since = parseSlot(context, "since", TwinIntegerMapped.class);

		Set<TwinAttributeMapped> attr = parseSlot(context, "sinceUnit", TwinAttributeMapped.class);
		if (!attr.isEmpty()) {
			sinceUnit = context.extractEnum(
					attr.iterator().next(),
					EnumTimeUnit.class
			);
		}
		Set<TwinAttributeMapped> orderByAttr = parseSlot(context, "orderBy", TwinAttributeMapped.class);

		if(!orderByAttr.isEmpty()){
			orderBy = context.extractEnum(orderByAttr.iterator().next(), EnumOrderBy.class);
		}


		limit = parseSlot(context, "limit", TwinIntegerMapped.class);

		filterExpression = parseSlot(context, "filterExpression", TwinBooleanMapped.class);

		result = parseSlot(context, "result", TwinAttributeMapped.class);

		validateZeroToMany(context, result, "result");
	}

	private void validateOnlyRedefinitions(
			MappingContext context
	) throws MappingException {

		for (var feature : getSysmlElement().getFeature()) {

			if (context.getUtils().isFromStandardOrDTLibrary(feature)) {
				continue;
			}

			boolean hasPlainSubsetting =
					feature.getOwnedSubsetting().stream()
							.anyMatch(subsetting ->
									!(subsetting instanceof Redefinition)
							);

			if (hasPlainSubsetting) {
				throw new MappingException(
						"%s '%s': feature '%s' must not subset a query slot."
								.formatted(
										getClass().getSimpleName(),
										getName(),
										feature.getName()
								)
				);
			}
		}
	}

	protected <T extends TwinAttributeMapped> Set<T> parseSlot(MappingContext context, String slotName, Class<T> clazz) throws MappingException {

		return new HashSet<>(context.mapSlot(this, slotName, clazz));
	}

	protected void validateZeroToMany(MappingContext context, Set<TwinAttributeMapped> attributes, String slotName) throws MappingException {

		for (TwinAttributeMapped attribute : attributes) {

			ElemWithMult multiplicity = context.getUtils().getMultiplicityRange(attribute.getSysmlElement());

			if (multiplicity.getLowerBound() != 0 || multiplicity.getUpperBound() != -1) {

				throw new MappingException("%s '%s': slot '%s' must have multiplicity [0..*], but found [%d..%s].".formatted(getClass().getSimpleName(), getName(), slotName, multiplicity.getLowerBound(), multiplicity.getUpperBound() == -1 ? "*" : multiplicity.getUpperBound()));
			}
		}
	}

	@Override
	public TwinAttribute getTwinAttribute() {
		return twinAttributes.stream()
				.findFirst()
				.orElseThrow();
	}

	@Override
	public Optional<TwinIntegerAttribute> getSince() {
		return since.stream()
				.map(x -> (TwinIntegerAttribute) x)
				.findFirst();
	}

	@Override
	public Optional<EnumTimeUnit> getSinceUnit() {
		return Optional.ofNullable(sinceUnit);
	}

	@Override
	public Optional<EnumOrderBy> getOrderBy() {
		return Optional.ofNullable(orderBy);
	}

	@Override
	public Optional<TwinIntegerAttribute> getLimit() {
		return limit.stream()
				.map(x -> (TwinIntegerAttribute) x)
				.findFirst();
	}

	@Override
	public Optional<TwinBooleanAttribute> getFilterExpression() {
		return filterExpression.stream()
				.map(x -> (TwinBooleanAttribute) x)
				.findFirst();
	}

	@Override
	public List<TwinAttribute> getResult() {
		return new ArrayList<>(result);
	}
}