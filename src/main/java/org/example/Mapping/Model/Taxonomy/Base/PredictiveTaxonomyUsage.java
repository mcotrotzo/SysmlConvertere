package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type PredictiveTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PredictiveTaxonomy", core = TaxonomyCore.class)
public class PredictiveTaxonomyUsage<C extends TaxonomyCore, D extends PredictiveTaxonomyDefinition<?>> extends CloudTwinTaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public PredictiveTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
