package org.example.Mapping.Model.Taxonomy.PrescriptiveModel;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Flow.PrescriptiveFlowUsage;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class PrescriptiveModelCore extends TaxonomyCore {
	@Getter private List<TwinStrategyUsage<?, ?>> prescriptiveStrategies = List.of();
	@Getter private List<PrescriptiveFlowUsage> prescriptiveFlows = List.of();

	public PrescriptiveModelCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		prescriptiveStrategies = mapper.mapSlot("prescriptiveStrategies", owner, Slots.<TwinStrategyUsage<?, ?>>rawClassOf(TwinStrategyUsage.class));
		prescriptiveFlows = mapper.mapSlot("prescriptiveFlows", owner, PrescriptiveFlowUsage.class);
	}
}
