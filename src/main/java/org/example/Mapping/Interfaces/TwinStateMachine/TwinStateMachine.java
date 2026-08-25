package org.example.Mapping.Interfaces.TwinStateMachine;

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
	List<TwinStateMachine<Usage>> getStates();

	List<Transition> getTransitions();

	Action<Usage> getEntryAction();

	Action<Usage> getExitAction();

	Action<Usage> getDoAction();


}
