package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeIntegerMapped;
import org.example.Mapping.TwinAttributeMapped.EnumOrderByMapped;
import org.example.Mapping.TwinAttributeMapped.EnumTimeUnitMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.QUERY_FLOW)
public class QueryFlowMapped<T extends TypeKind> extends FlowMapped<T> implements QueryFlow<T> {
	private TwinBaseAttributeIntegerMapped<Usage> since;
	private Optional<? extends org.example.Mapping.TwinAttributeMapped.EnumAttribute<EnumTimeUnit>> sinceUnit = Optional.empty();
	private Optional<? extends org.example.Mapping.TwinAttributeMapped.EnumAttribute<EnumOrderBy>> orderBy = Optional.empty();
	private TwinBaseAttributeIntegerMapped<Usage> limit;

	public QueryFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public QueryFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		since = mapOptionalConfigAttribute(context, "since", TwinBaseAttributeIntegerMapped.getRawIntegerUsageClass());

		sinceUnit = parseOptionalSlot(context, "sinceUnit", EnumTimeUnitMapped.class);

		orderBy = parseOptionalSlot(context, "orderBy", EnumOrderByMapped.class);

		limit = mapOptionalConfigAttribute(context, "limit", TwinBaseAttributeIntegerMapped.getRawIntegerUsageClass());
	}

	@Override
	public Optional<TwinBaseInteger<Usage>> since() {
		return Optional.ofNullable(since);
	}

	@Override
	public Optional<? extends EnumAttribute<EnumTimeUnit>> sinceUnit() {
		return sinceUnit;
	}

	@Override
	public Optional<? extends EnumAttribute<EnumOrderBy>> orderBy() {
		return orderBy;
	}

	@Override
	public Optional<TwinBaseInteger<Usage>> limit() {
		return Optional.ofNullable(limit);
	}

	private TwinBaseAttributeIntegerMapped<Usage> mapOptionalConfigAttribute(MappingContext context, String slotName, Class<TwinBaseAttributeIntegerMapped<Usage>> expectedClass) throws MappingException {

		List<TwinBaseAttributeIntegerMapped<Usage>> attributes = context.mapAttributes(this, slotName, expectedClass, Role.CONFIG);

		if (attributes.size() > 1) {
			throw new MappingException("QueryFlow '%s' has more than one '%s'.".formatted(getName(), slotName));
		}

		return attributes.isEmpty() ? null : attributes.getFirst();
	}


	private <T extends MappedElement<?, ?>> Optional<T> parseOptionalSlot(MappingContext context, String slotName, Class<T> clazz) throws MappingException {

		List<T> values = context.mapSlot(this, slotName, clazz);

		if (values.size() > 1) {
			throw new MappingException("%s '%s': slot '%s' may be specified at most once, but found %d.".formatted(getClass().getSimpleName(), getName(), slotName, values.size()));
		}

		return values.stream().findFirst();
	}

}
