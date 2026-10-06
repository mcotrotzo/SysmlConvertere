package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

public class PrescriptiveFlowUsage extends TwinFlowUsage {
	public PrescriptiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<PrescriptiveTaxonomyUsage> getSourceTaxonomy() {
		return PrescriptiveTaxonomyUsage.class;
	}

	@Override
	public Class<PrescriptiveTaxonomyUsage> getTargetTaxonomy() {
		return PrescriptiveTaxonomyUsage.class;
	}
}
