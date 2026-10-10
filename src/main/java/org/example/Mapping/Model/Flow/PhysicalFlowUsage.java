package org.example.Mapping.Model.Flow;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyUsage;


import org.omg.sysml.lang.sysml.FlowUsage;

@MappedLibrary(libraryName = "TwinActionLibrary::PhysicalFlow", core = EmptyCore.class)
public class PhysicalFlowUsage extends TwinFlowUsage {
	public PhysicalFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public Class<PhysicalTaxonomyUsage> getSourceTaxonomy() {
		return PhysicalTaxonomyUsage.class;
	}

	@Override
	public Class<PhysicalTaxonomyUsage> getTargetTaxonomy() {
		return PhysicalTaxonomyUsage.class;
	}
}
