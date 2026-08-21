package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
/**
 * Represents a state machine in the system. A state machine is also a state
 */
public interface StateMachine extends Model {
	List<TwinAttributeUsage> getLocalAttributes();

	List<StateMachine> getStates();

	List<Transition> getTransitions();

	Action getEntryAction();

	Action getExitAction();

	Action getDoAction();

	List<Succession> getSuccession();
}
