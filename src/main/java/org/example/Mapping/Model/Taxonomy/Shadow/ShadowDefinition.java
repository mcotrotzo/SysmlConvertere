package org.example.Mapping.Model.Taxonomy.Shadow;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.ShadowTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class ShadowDefinition extends ShadowTaxonomyDefinition<ShadowCore> implements ShadowCoreApi {
	public ShadowDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ShadowCore(sysmlElement, mapper), mapper);
	}
}
