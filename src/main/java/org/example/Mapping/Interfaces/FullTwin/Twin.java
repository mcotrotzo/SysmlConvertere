package org.example.Mapping.Interfaces.FullTwin;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;

import java.util.List;
import java.util.Optional;

public interface Twin<T extends TypeKind> extends CloudTwinTaxonomy<T> {

	Optional<PhysicalTwin<Usage>> getPhysicalTwin();

	Optional<Shadow<Usage>> getShadow();

	Optional<DescriptiveModel<Usage>> getDescriptiveModel();

	Optional<PredictiveModel<Usage>> getPredictiveModel();

	Optional<PrescriptiveModel<Usage>> getPrescriptiveModel();

	List<QueryFlow<Usage>> getQueryFlows();

	List<Flow<Usage>> getDescriptiveToPredictiveFlows();

	List<Flow<Usage>> getDescriptiveToPrescriptiveFlows();

	List<Flow<Usage>> getPredictiveToPrescriptiveFlows();

	List<Flow<Usage>> getPrescriptiveToPhysicalFlows();
}


