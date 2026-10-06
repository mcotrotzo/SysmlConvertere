package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type PhysicalTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class PhysicalTaxonomyUsage<C extends TaxonomyCore, D extends PhysicalTaxonomyDefinition> extends TaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public PhysicalTaxonomyUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
