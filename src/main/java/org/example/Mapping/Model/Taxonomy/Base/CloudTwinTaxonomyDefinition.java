package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type CloudTwinTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
public class CloudTwinTaxonomyDefinition<C extends TaxonomyCore> extends TaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public CloudTwinTaxonomyDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
