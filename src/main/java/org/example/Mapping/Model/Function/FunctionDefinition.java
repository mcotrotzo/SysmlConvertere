package org.example.Mapping.Model.Function;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

/** Common roof of base functions and user calculations; still a TwinActionBlockDefinition. */
public abstract class FunctionDefinition<C extends ActionBlockCore> extends TwinActionBlockDefinition<C> {
	protected FunctionDefinition(Behavior sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
