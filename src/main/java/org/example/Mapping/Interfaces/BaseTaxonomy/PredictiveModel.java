package org.example.Mapping.Interfaces.BaseTaxonomy;


import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;

import java.util.List;

public interface PredictiveModel extends Taxonomy{
	List<StrategyUsage> getPredictiveStrategies();
	List<FlowUsage> getPredictiveFlows();

	@Override
	default Context getContext() {
		return Context.PREDICTIVE;
	}
}
