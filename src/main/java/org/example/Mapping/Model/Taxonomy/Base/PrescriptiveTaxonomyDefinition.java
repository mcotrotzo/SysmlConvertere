package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type PrescriptiveTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::PrescriptiveTaxonomy", core = TaxonomyCore.class)
public class PrescriptiveTaxonomyDefinition<C extends TaxonomyCore> extends CloudTwinTaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public PrescriptiveTaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
