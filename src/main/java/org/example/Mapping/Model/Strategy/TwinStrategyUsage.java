package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;

/** Library type Strategy. Intermediate class: core and definition class come from the subclass or the registry. */
public class TwinStrategyUsage<C extends ActionBlockCore, D extends TwinStrategyDefinition> extends TwinActionBlockUsage<C, D> {
	public TwinStrategyUsage(ActionUsage sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
