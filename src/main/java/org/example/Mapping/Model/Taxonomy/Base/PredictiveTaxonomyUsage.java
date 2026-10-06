package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type PredictiveTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class PredictiveTaxonomyUsage<C extends TaxonomyCore, D extends PredictiveTaxonomyDefinition> extends CloudTwinTaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public PredictiveTaxonomyUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
