package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

/** Library type Strategy. Intermediate class: the core is passed in by the subclass or the registry. */
public class TwinStrategyDefinition<C extends ActionBlockCore> extends TwinActionBlockDefinition<C> {
	public TwinStrategyDefinition(Behavior sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
