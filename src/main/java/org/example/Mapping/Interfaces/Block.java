package org.example.Mapping.Interfaces;


import java.util.List;
/**
 * Represents a block in the model, which is a collection of actions and successions.
 */
public interface Block extends Action {
	List<Action> getActions();

	List<Succession> getSuccessions();
}
