package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

public class PredictiveFlowUsage extends TwinFlowUsage {
	public PredictiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<PredictiveTaxonomyUsage> getSourceTaxonomy() {
		return PredictiveTaxonomyUsage.class;
	}

	@Override
	public Class<PredictiveTaxonomyUsage> getTargetTaxonomy() {
		return PredictiveTaxonomyUsage.class;
	}
}
