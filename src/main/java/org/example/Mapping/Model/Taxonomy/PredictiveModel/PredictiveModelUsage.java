package org.example.Mapping.Model.Taxonomy.PredictiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PredictiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::PredictiveModel", core = PredictiveModelCore.class)
public class PredictiveModelUsage extends PredictiveTaxonomyUsage<PredictiveModelCore, PredictiveModelDefinition> implements PredictiveModelCoreApi {
	public PredictiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
