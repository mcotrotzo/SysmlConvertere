package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.List;

public interface Transition extends ActionUsage {
	Reference<? extends Action> getSource();

	Reference<? extends Action> getTarget();
	/**
	 * Returns the guard expressions associated with this transition.
	 *
	 * @return a list of guard expressions
	 */
	List<TwinExpression> getGuard();

	/**
	 * Returns the effect action associated with this transition. It is executed when the transition is triggered and the guard conditions are satisfied.
	 *
	 * @return the effect action
	 */
	Action getEffectAction();
}
