package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class ActuatorDefinition extends TwinPortDefinition<ActuatorCore> implements ActuatorCoreApi {
	public ActuatorDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ActuatorCore(sysmlElement, mapper), mapper);
	}
}
