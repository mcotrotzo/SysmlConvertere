package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::PrescriptiveModel", core = PrescriptiveModelCore.class)
public class PrescriptiveModelUsage extends PrescriptiveTaxonomyUsage<PrescriptiveModelCore, PrescriptiveModelDefinition> implements PrescriptiveModelCoreApi {
	public PrescriptiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
