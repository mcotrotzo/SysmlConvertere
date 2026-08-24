package org.example.Mapping.Interfaces.FullTwin;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.TwinFlow.FlowUsage;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlowUsage;
import org.example.Mapping.NewVersion.TwinFlow.Usage.FlowUsageMapped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Twin extends Type {

	Optional<PhysicalTwinUsage> getPhysicalTwin();
	Optional<ShadowUsage> getShadow();
	Optional<DescriptiveModelUsage> getDescriptiveModel();
	Optional<PredictiveModelUsage> getPredictiveModel();
	Optional<PrescriptiveModelUsage> getPrescriptiveModel();
	List<QueryFlowUsage> getQueryFlows();
	List<FlowUsage> getDescriptiveToPredictiveFlows();
	List<FlowUsage> getDescriptiveToPrescriptiveFlows();
	List<FlowUsage> getPredictiveToPrescriptiveFlows();
	List<FlowUsage> getPrescriptiveToPhysicalFlows();
}


