package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type PrescriptiveTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
public class PrescriptiveTaxonomyDefinition<C extends TaxonomyCore> extends CloudTwinTaxonomyDefinition<C> implements TaxonomyCoreApi<C> {
	public PrescriptiveTaxonomyDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
