package org.example.Mapping.Interfaces;

import java.util.List;

public interface PrescriptiveStrategy extends Strategy {
	List<TwinBooleanAttribute> getCondition();

	List<TriggerConfiguration> getTriggerConfiguration();

}
