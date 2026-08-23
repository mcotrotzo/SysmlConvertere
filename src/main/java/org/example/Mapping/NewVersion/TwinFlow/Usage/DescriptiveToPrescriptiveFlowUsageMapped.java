package org.example.Mapping.NewVersion.TwinFlow.Usage;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_TO_PRESCRIPTIVE_FLOW)
public class DescriptiveToPrescriptiveFlowUsageMapped extends FlowUsageMapped {
    public DescriptiveToPrescriptiveFlowUsageMapped(org.omg.sysml.lang.sysml.FlowUsage sysmlElement) {
        super(sysmlElement);
    }

    @Override
    protected Set<Context> sourceContexts() {
        return Set.of(Context.DESCRIPTIVE);
    }

    @Override
    protected Set<Context> targetContexts() {
        return Set.of(Context.PRESCRIPTIVE);
    }
}
