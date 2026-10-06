package org.example.Mapping.Model.Taxonomy.Shadow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.ShadowTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class ShadowUsage extends ShadowTaxonomyUsage<ShadowCore, ShadowDefinition> implements ShadowCoreApi {
	public ShadowUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ShadowCore(sysmlElement, mapper), mapper, ShadowDefinition.class);
	}
}
