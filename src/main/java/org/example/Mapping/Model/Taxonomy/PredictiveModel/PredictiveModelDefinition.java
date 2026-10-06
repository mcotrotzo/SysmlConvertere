package org.example.Mapping.Model.Taxonomy.PredictiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class PredictiveModelDefinition extends PredictiveTaxonomyDefinition<PredictiveModelCore> implements PredictiveModelCoreApi {
	public PredictiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PredictiveModelCore(sysmlElement, mapper), mapper);
	}
}
