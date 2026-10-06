package org.example.Mapping.Model.Taxonomy.PhysicalTwin;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class PhysicalTwinDefiniton extends PhysicalTaxonomyDefinition<PhysicalTwinCore> implements PhysicalTwinCoreApi {
	public PhysicalTwinDefiniton(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PhysicalTwinCore(sysmlElement, mapper), mapper);
	}
}
