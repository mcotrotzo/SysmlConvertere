package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.ControlUnit;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;

import java.util.List;

public interface PhysicalTwin extends Taxonomy {

	List<SensorUsage> getSensors();

	List<ActuatorUsage> getActuators();

	List<TwinStateMachineUsage> getControlUnits();

	List<TwinAttributeUsage> getConstAttributes();

	@Override
	default Context getContext() {
		return Context.PHYSICAL;
	}
}
