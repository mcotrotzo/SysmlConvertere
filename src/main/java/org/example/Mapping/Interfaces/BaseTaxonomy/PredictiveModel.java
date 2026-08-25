package org.example.Mapping.Interfaces.BaseTaxonomy;


import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

import java.util.List;

public interface PredictiveModel<T extends TypeKind> extends PredictiveTaxonomy<T> {
	List<Strategy<Usage>> getPredictiveStrategies();

	List<Flow<Usage>> getPredictiveFlows();
}
