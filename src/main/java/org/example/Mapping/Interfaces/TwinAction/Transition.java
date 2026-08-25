package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;

import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.List;

public interface Transition extends Action<Usage> {
	Reference<? extends Action<Usage>> getSource();

	Reference<? extends Action<Usage>> getTarget();
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
	Action<Usage> getEffectAction();
}
