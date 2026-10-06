package org.example.Mapping.Model.Federation;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

public class FederationTwinUsage extends Usage<FederationTwinCore, Feature, FederationTwinDefinition> implements FederationTwinCoreApi {
	public FederationTwinUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new FederationTwinCore(sysmlElement, mapper), mapper, FederationTwinDefinition.class);
	}
}
