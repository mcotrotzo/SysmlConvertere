package org.example.Mapping.Model.Twin;

import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCoreApi;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowUsage;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Flow.QueryFlowUsage;
import org.example.Mapping.Model.Flow.DescriptiveToPredictiveFlowUsage;
import org.example.Mapping.Model.Flow.DescriptiveToPrescriptiveFlowUsage;
import org.example.Mapping.Model.Flow.PredictiveToPrescriptiveFlowUsage;
import org.example.Mapping.Model.Flow.PrescriptiveToPhysicalFlowUsage;

import java.util.List;
import java.util.Optional;

public interface TwinCoreApi extends TaxonomyCoreApi<TwinCore> {
	default Optional<PhysicalTwinUsage> getPhysicalTwin() { return getCore().getPhysicalTwin(); }
	default Optional<ShadowUsage> getShadow() { return getCore().getShadow(); }
	default Optional<DescriptiveModelUsage> getDescriptiveModel() { return getCore().getDescriptiveModel(); }
	default Optional<PrescriptiveModelUsage> getPrescriptiveModel() { return getCore().getPrescriptiveModel(); }
	default Optional<PredictiveModelUsage> getPredictiveModel() { return getCore().getPredictiveModel(); }
	default List<QueryFlowUsage> getQueryFlows() { return getCore().getQueryFlows(); }
	default List<DescriptiveToPredictiveFlowUsage> getDescriptiveToPredictiveFlows() { return getCore().getDescriptiveToPredictiveFlows(); }
	default List<DescriptiveToPrescriptiveFlowUsage> getDescriptiveToPrescriptiveFlows() { return getCore().getDescriptiveToPrescriptiveFlows(); }
	default List<PredictiveToPrescriptiveFlowUsage> getPredictiveToPrescriptiveFlows() { return getCore().getPredictiveToPrescriptiveFlows(); }
	default List<PrescriptiveToPhysicalFlowUsage> getPrescriptiveToPhysicalFlows() { return getCore().getPrescriptiveToPhysicalFlows(); }
}
