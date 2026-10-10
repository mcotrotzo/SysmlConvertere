package org.example.Mapping.Model.Taxonomy.PhysicalTwin;


import Mapper.Mapper;
import Model.AbstractType;
import lombok.Getter;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Port.SensorUsage;
import org.example.Mapping.Model.Port.ActuatorUsage;
import org.example.Mapping.Model.Port.ConstPortUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Flow.PhysicalFlowUsage;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class PhysicalTwinCore extends TaxonomyCore {
	@Getter private List<SensorUsage> sensors = List.of();
	@Getter private List<ActuatorUsage> actuators = List.of();
	@Getter private List<TwinStateMachineUsage> controlUnits = List.of();
	@Getter private List<ConstPortUsage> constPorts = List.of();
	@Getter private List<PhysicalFlowUsage> physicalFlows = List.of();

	public PhysicalTwinCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		sensors = mapper.mapSlot("sensors", owner, SensorUsage.class);
		actuators = mapper.mapSlot("actuators", owner, ActuatorUsage.class);
		controlUnits = mapper.mapSlot("controlUnit", owner, TwinStateMachineUsage.class);
		constPorts = mapper.mapSlot("constPort", owner, ConstPortUsage.class);
		physicalFlows = mapper.mapSlot("physicalFlows", owner, PhysicalFlowUsage.class);
	}
}
