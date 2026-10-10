package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type PhysicalTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PhysicalTaxonomy", core = TaxonomyCore.class)
public class PhysicalTaxonomyDefinition<C extends TaxonomyCore> extends TaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public PhysicalTaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
