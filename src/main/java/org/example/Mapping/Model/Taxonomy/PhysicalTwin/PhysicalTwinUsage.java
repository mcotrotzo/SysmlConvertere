package org.example.Mapping.Model.Taxonomy.PhysicalTwin;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class PhysicalTwinUsage extends PhysicalTaxonomyUsage<PhysicalTwinCore, PhysicalTwinDefiniton> implements PhysicalTwinCoreApi {
	public PhysicalTwinUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PhysicalTwinCore(sysmlElement, mapper), mapper, PhysicalTwinDefiniton.class);
	}
}
