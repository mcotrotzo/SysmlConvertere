package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;

public interface Succession extends Action<Usage> {
	/**
	 * Returns a list of references to Action objects that are part of the succession.
	 * Order is important, as it defines the sequence of actions to be executed.
	 *
	 * @return a list of references to Action objects
	 */
	List<Reference<? extends Action<Usage>>> getActionList();
}
