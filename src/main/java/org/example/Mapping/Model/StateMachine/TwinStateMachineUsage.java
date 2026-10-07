package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.omg.sysml.lang.sysml.ActionUsage;

/** Library types TwinStateMachine, ControlUnit, DescriptiveStateMachine. */
public class TwinStateMachineUsage extends TwinTriggerActionUsage<TwinStateMachineCore, TwinStateMachineDefinition> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineUsage(ActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateMachineCore(sysmlElement, mapper), mapper, TwinStateMachineDefinition.class);
	}
}
