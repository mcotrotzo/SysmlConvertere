package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type DescriptiveTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::DescriptiveTaxonomy", core = TaxonomyCore.class)
public class DescriptiveTaxonomyUsage<C extends TaxonomyCore, D extends DescriptiveTaxonomyDefinition<?>> extends CloudTwinTaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public DescriptiveTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
