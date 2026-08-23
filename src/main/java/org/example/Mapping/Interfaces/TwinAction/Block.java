package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
/**
 * Represents a block in the model, which is a collection of actions and successions.
 */
public interface Block extends Action {
	List<TwinAttributeUsage> getInputs();
	List<TwinAttributeUsage> getOutputs();
	List<TwinAttributeUsage> localAttributes();
	List<Action> getActions();

	List<Succession> getSuccessions();
}
