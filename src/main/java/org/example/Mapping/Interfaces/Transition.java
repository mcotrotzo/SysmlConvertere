package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.List;

public interface Transition extends Action {
	Reference<? extends Action> getSource();

	Reference<? extends Action> getTarget();
	/**
	 * Returns the guard expressions associated with this transition.
	 *
	 * @return a list of guard expressions
	 */
	List<Expression> getGuard();

	/**
	 * Returns the effect action associated with this transition. It is executed when the transition is triggered and the guard conditions are satisfied.
	 *
	 * @return the effect action
	 */
	Action getEffectAction();
}
