package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.List;

public interface Transition extends Action {
	Reference<? extends Action> getSource();

	Reference<? extends Action> getTarget();

	List<Expression> getGuard();

	Action getEffectAction();
}
