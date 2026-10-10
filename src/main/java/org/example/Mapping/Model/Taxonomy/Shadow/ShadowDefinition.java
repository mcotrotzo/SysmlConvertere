package org.example.Mapping.Model.Taxonomy.Shadow;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.ShadowTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinDefLibrary::Shadow", core = ShadowCore.class)
public class ShadowDefinition extends ShadowTaxonomyDefinition<ShadowCore> implements ShadowCoreApi {
	public ShadowDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
