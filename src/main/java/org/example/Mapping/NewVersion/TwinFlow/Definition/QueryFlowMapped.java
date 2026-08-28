package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
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

import java.util.Optional;

@MappedElementType(LibraryNameSpaces.QUERY_FLOW)
public class QueryFlowMapped<T extends TypeKind>
		extends FlowMapped<T>
		implements QueryFlow<T> {

	private Optional<CompartmentMapped<TwinBaseAttributeIntegerMapped<Usage>>> since =
			Optional.empty();

	private Optional<CompartmentMapped<EnumTimeUnitMapped>> sinceUnit =
			Optional.empty();

	private Optional<CompartmentMapped<EnumOrderByMapped>> orderBy =
			Optional.empty();

	private Optional<CompartmentMapped<TwinBaseAttributeIntegerMapped<Usage>>> limit =
			Optional.empty();


	public QueryFlowMapped(FlowDefinition sysmlElement) {
		super(sysmlElement);
	}

	public QueryFlowMapped(FlowUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		since = parseOptionalSlot(
				context,
				"since",
				TwinBaseAttributeIntegerMapped.getRawIntegerUsageClass()
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
				TwinBaseAttributeIntegerMapped.getRawIntegerUsageClass()
		);
	}


	@Override
	public Optional<CompartmentMapped<TwinBaseAttributeIntegerMapped<Usage>>> since() {
		return since;
	}

	@Override
	public Optional<CompartmentMapped<EnumTimeUnitMapped>> sinceUnit() {
		return sinceUnit;
	}

	@Override
	public Optional<CompartmentMapped<EnumOrderByMapped>> orderBy() {
		return orderBy;
	}

	@Override
	public Optional<CompartmentMapped<TwinBaseAttributeIntegerMapped<Usage>>> limit() {
		return limit;
	}


	private <E extends MappedElement<?, Usage>>
	Optional<CompartmentMapped<E>> parseOptionalSlot(
			MappingContext context,
			String slotName,
			Class<E> clazz
	) throws MappingException {

		var values = context
				.mapSlot(this, slotName, clazz)
				.getCompartment();

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
}