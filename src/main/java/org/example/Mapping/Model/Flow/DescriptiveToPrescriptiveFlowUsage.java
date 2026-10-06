package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;

import org.omg.sysml.lang.sysml.FlowUsage;

public class DescriptiveToPrescriptiveFlowUsage extends TwinFlowUsage {
	public DescriptiveToPrescriptiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<DescriptiveTaxonomyUsage> getSourceTaxonomy() {
		return DescriptiveTaxonomyUsage.class;
	}

	@Override
	public Class<PrescriptiveTaxonomyUsage> getTargetTaxonomy() {
		return PrescriptiveTaxonomyUsage.class;
	}
}
