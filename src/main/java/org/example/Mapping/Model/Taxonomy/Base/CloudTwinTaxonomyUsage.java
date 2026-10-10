package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type CloudTwinTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::CloudTwinTaxonomy", core = TaxonomyCore.class)
public class CloudTwinTaxonomyUsage<C extends TaxonomyCore, D extends CloudTwinTaxonomyDefinition<?>> extends TaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public CloudTwinTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
