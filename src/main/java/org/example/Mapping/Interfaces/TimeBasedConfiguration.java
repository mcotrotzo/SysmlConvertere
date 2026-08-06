package org.example.Mapping.Interfaces;

import java.util.List;

public interface TimeBasedConfiguration extends TriggerConfiguration {
	List<TwinIntegerAttribute> getTriggerInterval();

	List<TwinAttribute> getTriggerIntervalUnit();
}
