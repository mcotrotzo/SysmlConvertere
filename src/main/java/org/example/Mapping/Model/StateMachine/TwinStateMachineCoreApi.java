package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Action.TwinTriggerActionCoreApi;

public interface TwinStateMachineCoreApi<C extends TwinStateMachineCore> extends TwinStateCoreApi<C>, TwinTriggerActionCoreApi<C> {
}
