package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinFlow.FlowDefinition;
import org.example.Mapping.TwinAction.Definition.TwinActionDefinitionMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import java.util.Set;

public abstract class FlowDefinitionMapped
        extends TwinActionDefinitionMapped<org.omg.sysml.lang.sysml.FlowDefinition>
        implements org.example.Mapping.Interfaces.TwinFlow.FlowDefinition {

    public FlowDefinitionMapped(org.omg.sysml.lang.sysml.FlowDefinition sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public void parse(MappingContext context) throws MappingException {
	}

    public abstract Set<Context> sourceContexts();

    public abstract Set<Context> targetContexts();
}
