package org.example.Mapping.Model.Taxonomy.DescriptiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class DescriptiveModelDefinition extends DescriptiveTaxonomyDefinition<DescriptiveModelCore> implements DescriptiveModelCoreApi {
	public DescriptiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DescriptiveModelCore(sysmlElement, mapper), mapper);
	}
}
