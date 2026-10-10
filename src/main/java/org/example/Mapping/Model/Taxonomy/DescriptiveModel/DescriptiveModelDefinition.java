package org.example.Mapping.Model.Taxonomy.DescriptiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::DescriptiveModel", core = DescriptiveModelCore.class)
public class DescriptiveModelDefinition extends DescriptiveTaxonomyDefinition<DescriptiveModelCore> implements DescriptiveModelCoreApi {
	public DescriptiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
