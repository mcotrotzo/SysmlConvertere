package org.example.Mapping.Model.StateMachine;

import org.example.Mapping.Model.Action.ActionBlockCoreApi;
import org.example.Mapping.Model.Action.TwinActionUsage;
import org.example.Mapping.Model.Action.TwinTransitionUsage;

import java.util.List;
import java.util.Optional;

public interface TwinStateMachineCoreApi<C extends TwinStateMachineCore> extends ActionBlockCoreApi<C> {
	default List<TwinStateMachineUsage> getStates() { return getCore().getStates(); }
	default List<TwinTransitionUsage> getTransitions() { return getCore().getTransitions(); }
	default Optional<TwinActionUsage<?, ?, ?>> getEntryAction() { return getCore().getEntryAction(); }
	default Optional<TwinActionUsage<?, ?, ?>> getDoAction() { return getCore().getDoAction(); }
	default Optional<TwinActionUsage<?, ?, ?>> getExitAction() { return getCore().getExitAction(); }
}
