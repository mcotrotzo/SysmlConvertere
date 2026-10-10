package org.example.Mapping.Model.Function;

import Mapper.Mapper;
import Model.Annotation.MappedLibrary;
import Model.Predefined.MetaClasses.Function.FunctionDefinition;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Action.ActionBlockCoreApi;
import org.omg.sysml.lang.sysml.Behavior;


@MappedLibrary(libraryName = "TwinImp::CustomCalculationAction", core = ActionBlockCore.class)
public class CustomCalculationDefinition extends FunctionDefinition<ActionBlockCore> implements ActionBlockCoreApi<ActionBlockCore> {
	public CustomCalculationDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
