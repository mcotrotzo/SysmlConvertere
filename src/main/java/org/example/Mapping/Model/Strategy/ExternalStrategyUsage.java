package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.ActionUsage;

public class ExternalStrategyUsage extends TwinStrategyUsage<ExternalStrategyCore, ExternalStrategyDefinition> implements ExternalStrategyCoreApi {
	public ExternalStrategyUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ExternalStrategyCore(sysmlElement, mapper), mapper, ExternalStrategyDefinition.class);
	}
}
