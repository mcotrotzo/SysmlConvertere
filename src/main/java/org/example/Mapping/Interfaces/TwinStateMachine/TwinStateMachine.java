package org.example.Mapping.Interfaces.TwinStateMachine;

import org.example.Mapping.Interfaces.TwinAction.Block;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Transition;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;

import java.util.List;
/**
 * Represents a state machine in the system. A state machine is also a state
 */
public interface TwinStateMachine extends Block {
	List<TwinStateMachineUsage> getStates();

	List<Transition> getTransitions();

	Action getEntryAction();

	Action getExitAction();

	Action getDoAction();


}
