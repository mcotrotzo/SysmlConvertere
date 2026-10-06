package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.PrescriptiveTaxonomyDefinition;
import org.omg.sysml.lang.sysml.Classifier;

public class PrescriptiveModelDefinition extends PrescriptiveTaxonomyDefinition<PrescriptiveModelCore> implements PrescriptiveModelCoreApi {
	public PrescriptiveModelDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new PrescriptiveModelCore(sysmlElement, mapper), mapper);
	}
}
