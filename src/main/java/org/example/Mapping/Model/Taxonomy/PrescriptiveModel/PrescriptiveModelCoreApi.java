package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.PrescriptiveFlowUsage;

import java.util.List;

public interface PrescriptiveModelCoreApi extends TaxonomyCoreApi<PrescriptiveModelCore> {
	default List<TwinStrategyUsage<?, ?>> getPrescriptiveStrategies() { return getCore().getPrescriptiveStrategies(); }
	default List<PrescriptiveFlowUsage> getPrescriptiveFlows() { return getCore().getPrescriptiveFlows(); }
}
