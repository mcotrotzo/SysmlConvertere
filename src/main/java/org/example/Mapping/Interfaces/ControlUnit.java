package org.example.Mapping.Interfaces;

import java.util.List;

public interface ControlUnit extends StateMachine {
	List<TriggerConfiguration> getTriggerConfiguration();

}
