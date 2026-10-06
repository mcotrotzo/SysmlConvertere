package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type DescriptiveTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class DescriptiveTaxonomyUsage<C extends TaxonomyCore, D extends DescriptiveTaxonomyDefinition> extends CloudTwinTaxonomyUsage<C, D> implements TaxonomyCoreApi<C> {
	public DescriptiveTaxonomyUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
