package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;

public class TwinActionBlockDefinition<C extends ActionBlockCore> extends TwinActionDefinition<C> implements ActionBlockCoreApi<C> {
	public TwinActionBlockDefinition(Behavior sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
