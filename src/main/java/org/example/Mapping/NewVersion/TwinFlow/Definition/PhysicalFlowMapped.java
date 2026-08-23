package org.example.Mapping.NewVersion.TwinFlow.Definition;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.FlowDefinition;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.PHYSICAL_FLOW)
public class PhysicalFlowMapped extends FlowDefinitionMapped {
    public PhysicalFlowMapped(FlowDefinition sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public Set<Context> sourceContexts() {
        return Set.of(Context.PHYSICAL);
    }

    @Override
    public Set<Context> targetContexts() {
        return Set.of(Context.PHYSICAL);
    }
}
