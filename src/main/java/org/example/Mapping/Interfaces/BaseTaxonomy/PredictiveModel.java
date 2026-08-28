package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

public interface PredictiveModel<T extends TypeKind>
		extends PredictiveTaxonomy<T> {

	CompartmentContainer<? extends Strategy<Usage>> getPredictiveStrategies();

	CompartmentContainer<? extends Flow<Usage>> getPredictiveFlows();
}