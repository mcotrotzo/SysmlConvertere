package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.DescriptiveTwinStateMachine;
import org.example.Mapping.Interfaces.DescriptiveStrategy;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;

public interface DescriptiveModel extends Taxonomy {

	List<TwinAttributeUsage> getDerivedAttributes();

	List<DescriptiveTwinStateMachine> getDescriptiveStateMachines();

	List<DescriptiveStrategy> getDescriptiveStrategies();

	@Override
	default Context getContext() {
		return Context.DESCRIPTIVE;
	}
}
