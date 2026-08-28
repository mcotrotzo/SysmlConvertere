package org.example.Mapping.Interfaces.FullTwin;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;

import java.util.List;
import java.util.Optional;

public interface Twin<T extends TypeKind> extends CloudTwinTaxonomy<T> {

	Optional<? extends Compartment<? extends PhysicalTwin<Usage>>> getPhysicalTwin();

	Optional<? extends Compartment<? extends Shadow<Usage>>> getShadow();

	Optional<? extends Compartment<? extends DescriptiveModel<Usage>>> getDescriptiveModel();

	Optional<? extends Compartment<? extends PredictiveModel<Usage>>> getPredictiveModel();

	Optional<? extends Compartment<? extends PrescriptiveModel<Usage>>> getPrescriptiveModel();

	CompartmentContainer<? extends QueryFlow<Usage>> getQueryFlows();

	CompartmentContainer<? extends Flow<Usage>> getDescriptiveToPredictiveFlows();

	CompartmentContainer<? extends Flow<Usage>> getDescriptiveToPrescriptiveFlows();

	CompartmentContainer<? extends Flow<Usage>> getPredictiveToPrescriptiveFlows();

	CompartmentContainer<? extends Flow<Usage>> getPrescriptiveToPhysicalFlows();
}


