package org.example.Mapping.Model.Taxonomy.PhysicalTwin;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::PhysicalTwin", core = PhysicalTwinCore.class)
public class PhysicalTwinDefiniton extends PhysicalTaxonomyDefinition<PhysicalTwinCore> implements PhysicalTwinCoreApi {
	public PhysicalTwinDefiniton(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
