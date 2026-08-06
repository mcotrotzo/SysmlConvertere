package org.example.Mapping.NewVersion;

import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinBooleanAttribute;
import org.example.Mapping.Interfaces.TwinIntegerAttribute;
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
	protected Set<TwinAttributeMapped> sinceUnit = new HashSet<>();
	protected Set<TwinAttributeMapped> orderBy = new HashSet<>();
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

		sinceUnit = parseSlot(context, "sinceUnit", TwinAttributeMapped.class);

		orderBy = parseSlot(context, "orderBy", TwinAttributeMapped.class);

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
	public List<TwinAttribute> getSinceUnit() {
		return new ArrayList<>(sinceUnit);
	}

	@Override
	public List<TwinAttribute> getOrderBy() {
		return new ArrayList<>(orderBy);
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