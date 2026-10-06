package org.example.Mapping.Model.Taxonomy.DescriptiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.DescriptiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class DescriptiveModelUsage extends DescriptiveTaxonomyUsage<DescriptiveModelCore, DescriptiveModelDefinition> implements DescriptiveModelCoreApi {
	public DescriptiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DescriptiveModelCore(sysmlElement, mapper), mapper, DescriptiveModelDefinition.class);
	}
}
