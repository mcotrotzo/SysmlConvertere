package org.example.Mapping.Model.Taxonomy.PredictiveModel;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.PredictiveFlowUsage;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class PredictiveModelCore extends TaxonomyCore {
	@Getter private List<TwinStrategyUsage<?, ?>> predictiveStrategies = List.of();
	@Getter private List<PredictiveFlowUsage> predictiveFlows = List.of();

	public PredictiveModelCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		predictiveStrategies = mapper.mapSlot("predictiveStrategies", owner, Slots.<TwinStrategyUsage<?, ?>>rawClassOf(TwinStrategyUsage.class));
		predictiveFlows = mapper.mapSlot("predictiveFlows", owner, PredictiveFlowUsage.class);
	}
}
