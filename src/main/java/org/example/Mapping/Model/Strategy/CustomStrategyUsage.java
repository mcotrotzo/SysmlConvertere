package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.omg.sysml.lang.sysml.ActionUsage;

public class CustomStrategyUsage extends TwinStrategyUsage<CustomStrategyCore, CustomStrategyDefinition> implements CustomStartegyCoreApi {
	public CustomStrategyUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new CustomStrategyCore(sysmlElement, mapper), mapper, CustomStrategyDefinition.class);
	}
}
