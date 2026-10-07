package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinTriggerActionDefinition;
import org.omg.sysml.lang.sysml.Behavior;

/** Library types TwinStateMachine, ControlUnit, DescriptiveStateMachine. */
public class TwinStateMachineDefinition extends TwinTriggerActionDefinition<TwinStateMachineCore> implements TwinStateMachineCoreApi<TwinStateMachineCore> {
	public TwinStateMachineDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinStateMachineCore(sysmlElement, mapper), mapper);
	}
}
