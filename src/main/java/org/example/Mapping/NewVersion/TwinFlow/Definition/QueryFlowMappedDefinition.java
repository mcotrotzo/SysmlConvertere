package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import EnumOrderBy;
import EnumTimeUnit;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlowDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinIntegerMappedUsage;
import org.example.Mapping.TwinAttributeMapped.EnumOrderByMapped;
import org.example.Mapping.TwinAttributeMapped.EnumTimeUnitMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.QUERY_FLOW)
public class QueryFlowMappedDefinition extends FlowDefinitionMapped implements QueryFlowDefinition {
    public QueryFlowMappedDefinition(FlowDefinition sysmlElement) {
        super(sysmlElement);
    }

	private TwinIntegerMappedUsage since;
	private Optional<EnumTimeUnitMapped> sinceUnit = Optional.empty();
	private Optional<EnumOrderByMapped> orderBy = Optional.empty();
	private TwinIntegerMappedUsage limit;


    @Override
    public Set<Context> sourceContexts() {
        return Set.of(Context.PHYSICAL);
    }

    @Override
    public Set<Context> targetContexts() {
        return Set.of(Context.PHYSICAL, Context.DESCRIPTIVE, Context.PREDICTIVE, Context.PRESCRIPTIVE, Context.SHADOW);
    }


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		since = mapOptionalConfigAttribute(
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

		limit = mapOptionalConfigAttribute(
				context,
				"limit",
				TwinIntegerMappedUsage.class
		);
	}

	@Override
	public Optional<TwinBaseIntegerUsage> since() {
		return Optional.ofNullable(since);
	}

	@Override
	public Optional<EnumTimeUnit> sinceUnit() {
		return sinceUnit.map(EnumTimeUnitMapped::getValue);
	}

	@Override
	public Optional<EnumOrderBy> orderBy() {
		return orderBy.map(EnumOrderByMapped::getValue);
	}

	@Override
	public Optional<TwinBaseIntegerUsage> limit() {
		return Optional.ofNullable(limit);
	}

	private TwinIntegerMappedUsage mapOptionalConfigAttribute(
			MappingContext context,
			String slotName,
			Class<TwinIntegerMappedUsage> expectedClass
	) throws MappingException {

		List<TwinIntegerMappedUsage> attributes =
				context.mapAttributes(
						this,
						slotName,
						expectedClass,
						Role.CONFIG
				);

		if (attributes.size() > 1) {
			throw new MappingException(
					"QueryFlow '%s' has more than one '%s'."
							.formatted(getName(), slotName)
			);
		}

		return attributes.isEmpty()
				? null
				: attributes.getFirst();
	}


	private  <T extends MappedElement<?>> Optional<T> parseOptionalSlot(
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

}
