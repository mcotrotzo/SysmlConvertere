package org.example.Mapping.Model.Taxonomy.DescriptiveModel;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.DescriptiveFlowUsage;

import java.util.List;

public interface DescriptiveModelCoreApi extends TaxonomyCoreApi<DescriptiveModelCore> {
	default List<TwinActionBlockUsage<?, ?>> getDerivedAttributes() { return getCore().getDerivedAttributes(); }
	default List<TwinStateMachineUsage> getDescriptiveStateMachines() { return getCore().getDescriptiveStateMachines(); }
	default List<TwinStrategyUsage<?, ?>> getDescriptiveStrategies() { return getCore().getDescriptiveStrategies(); }
	default List<DescriptiveFlowUsage> getDescriptiveFlows() { return getCore().getDescriptiveFlows(); }
}
