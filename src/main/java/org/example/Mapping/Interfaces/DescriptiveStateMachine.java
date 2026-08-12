package org.example.Mapping.Interfaces;

import java.util.List;

public interface DescriptiveStateMachine extends StateMachine {
	/**
	 * Returns the list of trigger configurations associated with this descriptive state machine.
	 *
	 * @return a list of TriggerConfiguration objects
	 */
	List<TriggerConfiguration> getTriggerConfiguration();

}
