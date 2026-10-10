package org.example.Mapping.Model.Taxonomy.Base;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type TwinTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "TwinTaxonomyLibrary::TwinTaxonomy", core = TaxonomyCore.class)
public class TaxonomyDefinition<C extends TaxonomyCore> extends Definition<C, Classifier> implements TaxonomyCoreApi<C> {
	public TaxonomyDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
