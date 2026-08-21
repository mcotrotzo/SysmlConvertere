package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;

public interface EventBasedConfiguration extends TriggerConfiguration {
	/**
	 * Returns a list of triggering attributes for the event-based configuration.
	 *
	 * @return a list of TwinAttribute objects that trigger the event-based configuration
	 */
	List<Reference<? extends TwinAttributeUsage>> getTriggeringAttributes();

	/**
	 * When true it only should be triggered when the value of triggering attribute changes
	 *
	 * @return a list of TwinBooleanAttribute
	 */
	TwinBooleanAttributeUsage getOnChange();
}
