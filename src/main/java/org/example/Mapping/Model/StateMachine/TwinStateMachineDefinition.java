package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockDefinition;
import org.omg.sysml.lang.sysml.Behavior;

/** Library types State, ControlUnit, ControlUnitState, DescriptiveStateMachine, DescriptiveState. */
public class TwinStateMachineDefinition extends TwinActionBlockDefinition<TwinStateMachineCore> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateMachineCore(sysmlElement, mapper), mapper);
	}
}
