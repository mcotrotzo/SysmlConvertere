package org.example.Mapping.Model.Twin;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.CloudTwinTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class TwinUsage extends CloudTwinTaxonomyUsage<TwinCore, TwinDefinition> implements TwinCoreApi {
	public TwinUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinCore(sysmlElement, mapper), mapper, TwinDefinition.class);
	}
}
