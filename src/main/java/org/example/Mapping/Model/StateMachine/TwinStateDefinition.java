package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

/** Library types State, ControlUnitState, DescriptiveState. */
public class TwinStateDefinition extends TwinActionBlockDefinition<TwinStateCore> implements TwinStateCoreApi<TwinStateCore> {
	public TwinStateDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateCore(sysmlElement, mapper), mapper);
	}
}
