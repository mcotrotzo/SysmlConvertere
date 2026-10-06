package org.example.Mapping.Model.Function;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.omg.sysml.lang.sysml.Behavior;

/** User calculation: calc def X :> CustomCalculationAction. */
public class CustomCalculationDefinition extends FunctionDefinition<ActionBlockCore> {
	public CustomCalculationDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new ActionBlockCore(sysmlElement, mapper), mapper);
	}
}
