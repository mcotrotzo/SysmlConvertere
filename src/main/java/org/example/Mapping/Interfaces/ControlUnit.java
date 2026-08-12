package org.example.Mapping.Interfaces;

import java.util.List;
/**
 * Represents a control unit in the model, which is a specialized type of state machine.
 */
public interface ControlUnit extends StateMachine {
	/**
	 * Retrieves the list of trigger configurations associated with this control unit.
	 *
	 * @return a list of TriggerConfiguration objects
	 */
	List<TriggerConfiguration> getTriggerConfiguration();

}
