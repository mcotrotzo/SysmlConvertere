package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.TwinAttribute.ConfigAttribute.TwinBooleanAttributeUsage;

import java.util.List;
import java.util.Optional;

public interface PrescriptiveStrategy extends Strategy {
	Optional<TwinBooleanAttributeUsage> getCondition();

	List<TriggerConfiguration> getTriggerConfiguration();

}
