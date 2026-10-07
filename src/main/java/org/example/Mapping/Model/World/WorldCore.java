package org.example.Mapping.Model.World;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Mapper;

import org.example.Mapping.Model.Twin.TwinUsage;
import org.example.Mapping.Model.Type.Core;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;

public class WorldCore extends Core<Type> {

	@Getter private List<TwinUsage> twins = List.of();
	@Getter private List<FederationFlowUsage> federatedLinks = List.of();

	public WorldCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		twins = mapper.mapSlot("twins", owner, TwinUsage.class);
		federatedLinks = mapper.mapSlot("federatedLinks", owner, FederationFlowUsage.class);
	}
}