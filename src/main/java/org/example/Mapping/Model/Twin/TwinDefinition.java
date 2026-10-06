package org.example.Mapping.Model.Twin;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.CloudTwinTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class TwinDefinition extends CloudTwinTaxonomyDefinition<TwinCore> implements TwinCoreApi {
	public TwinDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinCore(sysmlElement, mapper), mapper);
	}
}
