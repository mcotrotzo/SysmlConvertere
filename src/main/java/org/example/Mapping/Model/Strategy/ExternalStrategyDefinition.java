package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

public class ExternalStrategyDefinition extends TwinStrategyDefinition<ExternalStrategyCore> implements ExternalStrategyCoreApi {
	public ExternalStrategyDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ExternalStrategyCore(sysmlElement, mapper), mapper);
	}
}
