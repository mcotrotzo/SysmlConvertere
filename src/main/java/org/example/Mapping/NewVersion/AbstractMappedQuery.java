package org.example.Mapping.NewVersion;

import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.PartUsage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public List<TwinAttribute> getTwinAttribute() {
		return new ArrayList<>(twinAttributes);
	}

	@Override
	public List<TwinIntegerAttribute> getSince() {
		return new ArrayList<>(since);
	}

	@Override
	public EnumTimeUnit getSinceUnit() {
		return sinceUnit;
	}

	@Override
	public EnumOrderBy getOrderBy() {
		return orderBy;
	}

	@Override
	public List<TwinIntegerAttribute> getLimit() {
		return new ArrayList<>(limit);
	}

	@Override
	public List<TwinBooleanAttribute> getFilterExpression() {
		return new ArrayList<>(filterExpression);
	}

	@Override
	public List<TwinAttribute> getResult() {
		return new ArrayList<>(result);
	}
}