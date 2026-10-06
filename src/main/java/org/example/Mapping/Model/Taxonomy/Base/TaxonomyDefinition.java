package org.example.Mapping.Model.Taxonomy.Base;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type TwinTaxonomy. Intermediate class: the concrete core is passed in by the subclass or the registry. */
public class TaxonomyDefinition<C extends TaxonomyCore> extends Definition<C, Classifier> implements TaxonomyCoreApi<C> {
	public TaxonomyDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
