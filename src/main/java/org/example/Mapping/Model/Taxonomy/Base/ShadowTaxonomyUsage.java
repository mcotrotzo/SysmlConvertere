package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type ShadowTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::ShadowTaxonomy", core = TaxonomyCore.class)
public class ShadowTaxonomyUsage<C extends TaxonomyCore, D extends ShadowTaxonomyDefinition<?>> extends TaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public ShadowTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
