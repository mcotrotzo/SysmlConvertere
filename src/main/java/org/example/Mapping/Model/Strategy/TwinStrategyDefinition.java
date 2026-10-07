package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionDefinition;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

public class TwinStrategyDefinition<C extends TwinTriggerActionCore> extends TwinTriggerActionDefinition<C> {
	public TwinStrategyDefinition(Behavior sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
