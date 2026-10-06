package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

/** Library types State, ControlUnit, ControlUnitState, DescriptiveStateMachine, DescriptiveState. */
public class TwinStateMachineUsage extends TwinActionBlockUsage<TwinStateMachineCore, TwinStateMachineDefinition> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateMachineCore(sysmlElement, mapper), mapper, TwinStateMachineDefinition.class);
	}
}
