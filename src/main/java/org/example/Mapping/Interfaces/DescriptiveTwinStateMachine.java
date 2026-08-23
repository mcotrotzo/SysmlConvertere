package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;

import java.util.List;

public interface DescriptiveTwinStateMachine extends TwinStateMachine {
	/**
	 * Returns the list of trigger configurations associated with this descriptive state machine.
	 *
	 * @return a list of TriggerConfiguration objects
	 */
	List<TriggerConfiguration> getTriggerConfiguration();

}
