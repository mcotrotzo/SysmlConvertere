package org.example.Mapping.Interfaces;

import java.util.List;

public interface EventBasedConfiguration extends TriggerConfiguration {
	List<TwinAttribute> getTriggeringAttributes();

	List<TwinBooleanAttribute> getOnChange();
}
