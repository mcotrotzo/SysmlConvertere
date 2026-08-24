package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinStateMachine.Usage.TwinStateMachineUsage;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;

import java.util.List;

public interface DescriptiveModel extends Taxonomy {

	List<ActionUsage> getDerivedAttributes();

	List<TwinStateMachineUsage> getDescriptiveStateMachines();

	List<StrategyUsage> getDescriptiveStrategies();
	List<FlowUsage> getDescriptiveFlows();

	@Override
	default Context getContext() {
		return Context.DESCRIPTIVE;
	}
}
