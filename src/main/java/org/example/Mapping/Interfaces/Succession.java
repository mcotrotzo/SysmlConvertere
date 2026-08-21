package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Model;

import java.util.List;

public interface Succession extends Model {
	/**
	 * Returns a list of references to Action objects that are part of the succession.
	 * Order is important, as it defines the sequence of actions to be executed.
	 * @return a list of references to Action objects
	 */
	List<Reference<? extends Action>> getActionList();
}
