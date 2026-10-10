package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type PhysicalTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PhysicalTaxonomy", core = TaxonomyCore.class)
public class PhysicalTaxonomyUsage<C extends TaxonomyCore, D extends PhysicalTaxonomyDefinition<?>> extends TaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public PhysicalTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
