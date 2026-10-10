package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type PredictiveTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PredictiveTaxonomy", core = TaxonomyCore.class)
public class PredictiveTaxonomyDefinition<C extends TaxonomyCore> extends CloudTwinTaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public PredictiveTaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
