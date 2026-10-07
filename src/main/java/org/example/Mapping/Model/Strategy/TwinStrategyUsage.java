package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

import java.util.function.Supplier;


public class TwinStrategyUsage<C extends TwinTriggerActionCore, D extends TwinStrategyDefinition<?>> extends TwinTriggerActionUsage<C, D> {
	public TwinStrategyUsage(ActionUsage sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
