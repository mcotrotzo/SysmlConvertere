package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.omg.sysml.lang.sysml.Behavior;

public class CustomStrategyDefinition extends TwinStrategyDefinition<CustomStrategyCore> implements CustomStartegyCoreApi {
	public CustomStrategyDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new CustomStrategyCore(sysmlElement, mapper), mapper);
	}
}
