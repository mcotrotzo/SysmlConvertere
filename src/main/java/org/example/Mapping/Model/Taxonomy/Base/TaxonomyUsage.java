package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type TwinTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::TwinTaxonomy", core = TaxonomyCore.class)
public class TaxonomyUsage<C extends TaxonomyCore, D extends TaxonomyDefinition<?>> extends Usage<C, Feature, D> implements TaxonomyCoreApi<C> {
	public TaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
