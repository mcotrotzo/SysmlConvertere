package org.example.Mapping.Model.Taxonomy.PhysicalTwin;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PhysicalTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::PhysicalTwin", core = PhysicalTwinCore.class)
public class PhysicalTwinUsage extends PhysicalTaxonomyUsage<PhysicalTwinCore, PhysicalTwinDefiniton> implements PhysicalTwinCoreApi {
	public PhysicalTwinUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
