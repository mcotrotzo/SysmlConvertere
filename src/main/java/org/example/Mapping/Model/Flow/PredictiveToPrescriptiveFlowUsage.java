package org.example.Mapping.Model.Flow;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

@MappedLibrary(libraryName = "TwinActionLibrary::PredictiveToPrescriptiveFlow", core = EmptyCore.class)
public class PredictiveToPrescriptiveFlowUsage extends TwinFlowUsage {
	public PredictiveToPrescriptiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<PredictiveTaxonomyUsage> getSourceTaxonomy() {
		return PredictiveTaxonomyUsage.class;
	}

	@Override
	public Class<PrescriptiveTaxonomyUsage> getTargetTaxonomy() {
		return PrescriptiveTaxonomyUsage.class;
	}
}
