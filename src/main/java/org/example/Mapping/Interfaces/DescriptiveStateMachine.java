package org.example.Mapping.Interfaces;

import java.util.List;

public interface DescriptiveStateMachine extends StateMachine {
	List<TriggerConfiguration> getTriggerConfiguration();
}
