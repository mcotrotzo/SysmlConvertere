package org.example.Mapping.Model.Taxonomy.DescriptiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::DescriptiveModel", core = DescriptiveModelCore.class)
public class DescriptiveModelUsage extends DescriptiveTaxonomyUsage<DescriptiveModelCore, DescriptiveModelDefinition> implements DescriptiveModelCoreApi {
	public DescriptiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
