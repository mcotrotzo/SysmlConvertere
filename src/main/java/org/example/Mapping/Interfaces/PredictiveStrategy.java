package org.example.Mapping.Interfaces;

import java.util.List;

public interface PredictiveStrategy extends Strategy {
	/**
	 * Returns a list of trigger configurations associated with this predictive strategy.
	 *
	 * @return a list of TriggerConfiguration objects
	 */
	List<TriggerConfiguration> getTriggerConfiguration();
}
