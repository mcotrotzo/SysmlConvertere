package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ConstPortUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;

import java.util.List;
import java.util.Optional;

public interface PhysicalTwin extends Taxonomy {

	List<SensorUsage> getSensors();

	List<ActuatorUsage> getActuators();

	List<TwinStateMachineUsage> getControlUnits();
	List<FlowUsage> getPhysicalFlows();

	Optional<ConstPortUsage> getConstPort();


	@Override
	default Context getContext() {
		return Context.PHYSICAL;
	}
}
