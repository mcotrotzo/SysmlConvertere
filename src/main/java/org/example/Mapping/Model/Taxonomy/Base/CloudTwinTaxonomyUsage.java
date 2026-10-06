package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type CloudTwinTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class CloudTwinTaxonomyUsage<C extends TaxonomyCore, D extends CloudTwinTaxonomyDefinition> extends TaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public CloudTwinTaxonomyUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
