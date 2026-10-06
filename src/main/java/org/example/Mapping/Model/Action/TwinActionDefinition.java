package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Behavior;

import java.util.function.Supplier;


public abstract class TwinActionDefinition<C extends Core<? super Behavior>> extends Definition<C, Behavior> {
	protected TwinActionDefinition(Behavior sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
