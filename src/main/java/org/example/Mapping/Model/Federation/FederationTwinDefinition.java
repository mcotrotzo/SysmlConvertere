package org.example.Mapping.Model.Federation;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

public class FederationTwinDefinition extends Definition<FederationTwinCore, Classifier> implements FederationTwinCoreApi {
	public FederationTwinDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new FederationTwinCore(sysmlElement, mapper), mapper);
	}
}
