package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

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
