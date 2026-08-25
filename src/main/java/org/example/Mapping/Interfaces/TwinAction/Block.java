package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

import java.util.List;
/**
 * Represents a block in the model, which is a collection of actions and successions.
 */
public interface Block<T extends TypeKind> extends Action<T> {
	List<TwinAttribute<Usage>> getInputs();
	List<TwinAttribute<Usage>> getOutputs();
	List<TwinAttribute<Usage>> localAttributes();
	List<Action<Usage>> getActions();

	List<Succession> getSuccessions();
}
