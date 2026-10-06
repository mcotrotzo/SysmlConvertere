package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type TwinTaxonomy. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class TaxonomyUsage<C extends TaxonomyCore, D extends TaxonomyDefinition> extends Usage<C, Feature, D> implements TaxonomyCoreApi<C> {
	public TaxonomyUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
