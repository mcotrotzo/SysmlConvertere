package org.example.Mapping.Interfaces;

import java.util.List;

public interface StateMachine extends Model {
	List<TwinAttribute> getLocalAttributes();

	List<StateMachine> getStates();

	List<Transition> getTransitions();

	Action getEntryAction();

	Action getExitAction();

	Action getDoAction();

	List<Succession> getSuccession();
}
