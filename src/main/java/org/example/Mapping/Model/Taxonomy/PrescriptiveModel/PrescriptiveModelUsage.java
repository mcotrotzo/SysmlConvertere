package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyUsage;
import org.omg.sysml.lang.sysml.Feature;

public class PrescriptiveModelUsage extends PrescriptiveTaxonomyUsage<PrescriptiveModelCore, PrescriptiveModelDefinition> implements PrescriptiveModelCoreApi {
	public PrescriptiveModelUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PrescriptiveModelCore(sysmlElement, mapper), mapper, PrescriptiveModelDefinition.class);
	}
}
