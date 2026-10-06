package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

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
