package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

public interface PrescriptiveModel<T extends TypeKind>
		extends PrescriptiveTaxonomy<T> {

	CompartmentContainer<? extends Strategy<Usage>>
	getPrescriptiveStrategies();

	CompartmentContainer<? extends Flow<Usage>>
	getPrescriptiveFlows();
}