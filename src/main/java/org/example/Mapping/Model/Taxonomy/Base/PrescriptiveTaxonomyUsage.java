package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type PrescriptiveTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PrescriptiveTaxonomy", core = TaxonomyCore.class)
public class PrescriptiveTaxonomyUsage<C extends TaxonomyCore, D extends PrescriptiveTaxonomyDefinition<?>> extends CloudTwinTaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public PrescriptiveTaxonomyUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
