package org.example.Mapping.NewVersion.TwinFlow.Usage;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_FLOW)
public class PrescriptiveFlowUsageMapped extends FlowUsageMapped {
    public PrescriptiveFlowUsageMapped(org.omg.sysml.lang.sysml.FlowUsage sysmlElement) {
        super(sysmlElement);
    }

    @Override
    protected Set<Context> sourceContexts() {
        return Set.of(Context.PRESCRIPTIVE);
    }

    @Override
    protected Set<Context> targetContexts() {
        return Set.of(Context.PRESCRIPTIVE);
    }
}
