package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::PrescriptiveModel", core = PrescriptiveModelCore.class)
public class PrescriptiveModelDefinition extends PrescriptiveTaxonomyDefinition<PrescriptiveModelCore> implements PrescriptiveModelCoreApi {
	public PrescriptiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
