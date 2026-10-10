package org.example.Mapping.Model.Taxonomy.PredictiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::PredictiveModel", core = PredictiveModelCore.class)
public class PredictiveModelDefinition extends PredictiveTaxonomyDefinition<PredictiveModelCore> implements PredictiveModelCoreApi {
	public PredictiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
