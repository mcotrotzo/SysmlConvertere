package org.example.Mapping.Model.Federation;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Flow.FederationFlowUsage;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class FederationTwinCore extends Core<Type> {
	@Getter private List<FederationFlowUsage> federationFlows = List.of();

	public FederationTwinCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		federationFlows = mapper.mapSlot("federatedLinks", owner, FederationFlowUsage.class);
	}
}
