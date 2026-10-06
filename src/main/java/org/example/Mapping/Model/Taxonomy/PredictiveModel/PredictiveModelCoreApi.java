package org.example.Mapping.Model.Taxonomy.PredictiveModel;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.PredictiveFlowUsage;

import java.util.List;

public interface PredictiveModelCoreApi extends TaxonomyCoreApi<PredictiveModelCore> {
	default List<TwinStrategyUsage<?, ?>> getPredictiveStrategies() { return getCore().getPredictiveStrategies(); }
	default List<PredictiveFlowUsage> getPredictiveFlows() { return getCore().getPredictiveFlows(); }
}
