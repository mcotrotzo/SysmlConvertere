package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;

import org.omg.sysml.lang.sysml.FlowUsage;

public class DescriptiveFlowUsage extends TwinFlowUsage {
	public DescriptiveFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<DescriptiveTaxonomyUsage> getSourceTaxonomy() {
		return DescriptiveTaxonomyUsage.class;
	}

	@Override
	public Class<DescriptiveTaxonomyUsage> getTargetTaxonomy() {
		return DescriptiveTaxonomyUsage.class;
	}
}
