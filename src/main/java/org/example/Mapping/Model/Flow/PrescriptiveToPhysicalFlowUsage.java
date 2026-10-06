package org.example.Mapping.Model.Flow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyUsage;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;

import org.omg.sysml.lang.sysml.FlowUsage;

public class PrescriptiveToPhysicalFlowUsage extends TwinFlowUsage {
	public PrescriptiveToPhysicalFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<PrescriptiveTaxonomyUsage> getSourceTaxonomy() {
		return PrescriptiveTaxonomyUsage.class;
	}

	@Override
	public Class<PhysicalTaxonomyUsage> getTargetTaxonomy() {
		return PhysicalTaxonomyUsage.class;
	}
}
