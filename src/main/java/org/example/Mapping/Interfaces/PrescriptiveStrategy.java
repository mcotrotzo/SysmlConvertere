package org.example.Mapping.Interfaces;

import java.util.List;
import java.util.Optional;

public interface PrescriptiveStrategy extends Strategy {
	Optional<TwinBooleanAttributeUsage> getCondition();

	List<TriggerConfiguration> getTriggerConfiguration();

}
