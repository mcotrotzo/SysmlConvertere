package org.example.Mapping.Model.Taxonomy.PredictiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class PredictiveModelUsage extends PredictiveTaxonomyUsage<PredictiveModelCore, PredictiveModelDefinition> implements PredictiveModelCoreApi {
	public PredictiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PredictiveModelCore(sysmlElement, mapper), mapper, PredictiveModelDefinition.class);
	}
}
