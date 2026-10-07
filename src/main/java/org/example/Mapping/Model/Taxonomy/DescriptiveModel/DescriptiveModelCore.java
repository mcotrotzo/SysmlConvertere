package org.example.Mapping.Model.Taxonomy.DescriptiveModel;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.DescriptiveFlowUsage;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class DescriptiveModelCore extends TaxonomyCore {
	@Getter private List<TwinTriggerActionUsage<?, ?>> derivedAttributes = List.of();
	@Getter private List<TwinStateMachineUsage> descriptiveStateMachines = List.of();
	@Getter private List<TwinStrategyUsage<?, ?>> descriptiveStrategies = List.of();
	@Getter private List<DescriptiveFlowUsage> descriptiveFlows = List.of();

	public DescriptiveModelCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		derivedAttributes = mapper.mapSlot("derivedAttributes", owner, Slots.<TwinTriggerActionUsage<?, ?>>rawClassOf(TwinTriggerActionUsage.class));
		descriptiveStateMachines = mapper.mapSlot("descriptiveStateMachine_", owner, TwinStateMachineUsage.class);
		descriptiveStrategies = mapper.mapSlot("descriptiveStrategies", owner, Slots.<TwinStrategyUsage<?, ?>>rawClassOf(TwinStrategyUsage.class));
		descriptiveFlows = mapper.mapSlot("descriptiveFlows", owner, DescriptiveFlowUsage.class);
	}
}
