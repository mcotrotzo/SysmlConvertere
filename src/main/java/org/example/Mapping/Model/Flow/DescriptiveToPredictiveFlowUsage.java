package org.example.Mapping.Model.Flow;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

@MappedLibrary(libraryName = "TwinActionLibrary::DescriptiveToPredictiveFlow", core = EmptyCore.class)
public class DescriptiveToPredictiveFlowUsage extends TwinFlowUsage {
	public DescriptiveToPredictiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<DescriptiveTaxonomyUsage> getSourceTaxonomy() {
		return DescriptiveTaxonomyUsage.class;
	}

	@Override
	public Class<PredictiveTaxonomyUsage> getTargetTaxonomy() {
		return PredictiveTaxonomyUsage.class;
	}
}
