package org.example.Mapping.Model.Action;


import Mapper.Mapper;
import Model.Core.Core;
import Model.Definition;
import org.omg.sysml.lang.sysml.Behavior;



public abstract class TwinActionDefinition<C extends Core<? super Behavior>> extends Definition<C, Behavior> {
	protected TwinActionDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
