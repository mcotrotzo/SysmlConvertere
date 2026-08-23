package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_FLOW)
public class PredictiveFlowMapped extends FlowDefinitionMapped {
    public PredictiveFlowMapped(FlowDefinition sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public Set<Context> sourceContexts() {
        return Set.of(Context.PREDICTIVE);
    }

    @Override
    public Set<Context> targetContexts() {
        return Set.of(Context.PREDICTIVE);
    }
}
