package org.example.Mapping.Interfaces.TwinStateMachine;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Block;
import org.example.Mapping.Interfaces.TwinAction.Transition;

import java.util.List;

/**
 * Represents a state machine in the system. A state machine is also a state
 */
public interface TwinStateMachine<T extends TypeKind> extends Block<T> {

	CompartmentContainer<? extends TwinStateMachine<Usage>> getStates();

	List<Transition> getTransitions();

	Compartment<? extends Action<Usage>> getEntryAction();

	Compartment<? extends Action<Usage>> getExitAction();

	Compartment<? extends Action<Usage>> getDoAction();
}