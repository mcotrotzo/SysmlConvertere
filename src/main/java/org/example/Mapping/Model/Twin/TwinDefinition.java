package org.example.Mapping.Model.Twin;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.CloudTwinTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::Twin", core = TwinCore.class)
public class TwinDefinition extends CloudTwinTaxonomyDefinition<TwinCore> implements TwinCoreApi {
	public TwinDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
