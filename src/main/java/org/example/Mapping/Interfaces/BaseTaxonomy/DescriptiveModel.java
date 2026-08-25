package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;

import java.util.List;

public interface DescriptiveModel<T extends TypeKind> extends DescriptiveTaxonomy<T> {

	List<Action<Usage>> getDerivedAttributes();

	List<TwinStateMachine<Usage>> getDescriptiveStateMachines();

	List<Strategy<Usage>> getDescriptiveStrategies();

	List<Flow<Usage>> getDescriptiveFlows();

}
