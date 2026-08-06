package org.example.Mapping.Interfaces;

import java.util.List;

public interface PredictiveStrategy extends Strategy {
	List<TriggerConfiguration> getTriggerConfiguration();
}
