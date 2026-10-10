package org.example.Mapping.Model.StateMachine;

import Model.Predefined.MetaClasses.Action.ActionMapUsage;
import org.example.Mapping.Model.Action.ActionBlockCoreApi;
import org.example.Mapping.Model.Action.TwinTransitionUsage;

import java.util.List;
import java.util.Optional;

public interface TwinStateCoreApi<C extends TwinStateCore> extends ActionBlockCoreApi<C> {
	default List<TwinStateUsage> getStates() { return getCore().getStates(); }
	default List<TwinTransitionUsage> getTransitions() { return getCore().getTransitions(); }
	default Optional<ActionMapUsage<?, ?, ?>> getEntryAction() { return getCore().getEntryAction(); }
	default Optional<ActionMapUsage<?, ?, ?>> getDoAction() { return getCore().getDoAction(); }
	default Optional<ActionMapUsage<?, ?, ?>> getExitAction() { return getCore().getExitAction(); }
}
