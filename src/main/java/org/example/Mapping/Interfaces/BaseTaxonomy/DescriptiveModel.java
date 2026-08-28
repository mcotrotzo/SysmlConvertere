package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

import java.util.List;

public interface DescriptiveModel<T extends TypeKind> extends DescriptiveTaxonomy<T> {

	CompartmentContainer<? extends Action<Usage>> getDerivedAttributes();

	CompartmentContainer<? extends TwinStateMachine<Usage>> getDescriptiveStateMachines();

	CompartmentContainer<? extends Strategy<Usage>> getDescriptiveStrategies();

	CompartmentContainer<? extends Flow<Usage>> getDescriptiveFlows();

}
