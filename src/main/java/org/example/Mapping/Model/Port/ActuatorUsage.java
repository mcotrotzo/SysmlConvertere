package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class ActuatorUsage extends TwinPortUsage<ActuatorCore, ActuatorDefinition> implements ActuatorCoreApi {
	public ActuatorUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ActuatorCore(sysmlElement, mapper), mapper, ActuatorDefinition.class);
	}
}
