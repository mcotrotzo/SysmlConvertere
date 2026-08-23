package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_FLOW)
public class PrescriptiveFlowMapped extends FlowDefinitionMapped {
    public PrescriptiveFlowMapped(FlowDefinition sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public Set<Context> sourceContexts() {
        return Set.of(Context.PRESCRIPTIVE);
    }

    @Override
    public Set<Context> targetContexts() {
        return Set.of(Context.PRESCRIPTIVE);
    }
}
