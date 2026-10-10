package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type CloudTwinTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::CloudTwinTaxonomy", core = TaxonomyCore.class)
public class CloudTwinTaxonomyDefinition<C extends TaxonomyCore> extends TaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public CloudTwinTaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
