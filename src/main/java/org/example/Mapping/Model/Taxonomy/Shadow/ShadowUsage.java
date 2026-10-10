package org.example.Mapping.Model.Taxonomy.Shadow;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.ShadowTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinDefLibrary::Shadow", core = ShadowCore.class)
public class ShadowUsage extends ShadowTaxonomyUsage<ShadowCore, ShadowDefinition> implements ShadowCoreApi {
	public ShadowUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
