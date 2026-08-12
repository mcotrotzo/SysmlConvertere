package org.example.Mapping.Interfaces;

import java.util.List;
/**
 * Represents a state machine in the system. A state machine is also a state
 */
public interface StateMachine extends Model {
	List<TwinAttribute> getLocalAttributes();

	List<StateMachine> getStates();

	List<Transition> getTransitions();

	Action getEntryAction();

	Action getExitAction();

	Action getDoAction();

	List<Succession> getSuccession();
}
