package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type DescriptiveTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::DescriptiveTaxonomy", core = TaxonomyCore.class)
public class DescriptiveTaxonomyDefinition<C extends TaxonomyCore> extends CloudTwinTaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public DescriptiveTaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
