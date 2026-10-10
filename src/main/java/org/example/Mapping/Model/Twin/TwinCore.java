package org.example.Mapping.Model.Twin;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyCore;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowUsage;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Flow.QueryFlowUsage;
import org.example.Mapping.Model.Flow.DescriptiveToPredictiveFlowUsage;
import org.example.Mapping.Model.Flow.DescriptiveToPrescriptiveFlowUsage;
import org.example.Mapping.Model.Flow.PredictiveToPrescriptiveFlowUsage;
import org.example.Mapping.Model.Flow.PrescriptiveToPhysicalFlowUsage;
import java.util.List;
import java.util.Optional;
import org.omg.sysml.lang.sysml.Type;

public class TwinCore extends TaxonomyCore {
	@Getter private Optional<PhysicalTwinUsage> physicalTwin = Optional.empty();
	@Getter private Optional<ShadowUsage> shadow = Optional.empty();
	@Getter private Optional<DescriptiveModelUsage> descriptiveModel = Optional.empty();
	@Getter private Optional<PrescriptiveModelUsage> prescriptiveModel = Optional.empty();
	@Getter private Optional<PredictiveModelUsage> predictiveModel = Optional.empty();
	@Getter private List<QueryFlowUsage> queryFlows = List.of();
	@Getter private List<DescriptiveToPredictiveFlowUsage> descriptiveToPredictiveFlows = List.of();
	@Getter private List<DescriptiveToPrescriptiveFlowUsage> descriptiveToPrescriptiveFlows = List.of();
	@Getter private List<PredictiveToPrescriptiveFlowUsage> predictiveToPrescriptiveFlows = List.of();
	@Getter private List<PrescriptiveToPhysicalFlowUsage> prescriptiveToPhysicalFlows = List.of();

	public TwinCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		physicalTwin = Slots.atMostOne(owner, "physicalTwin", mapper.mapSlot("physicalTwin", owner, PhysicalTwinUsage.class));
		shadow = Slots.atMostOne(owner, "shadow", mapper.mapSlot("shadow", owner, ShadowUsage.class));
		descriptiveModel = Slots.atMostOne(owner, "descriptiveModel", mapper.mapSlot("descriptiveModel", owner, DescriptiveModelUsage.class));
		prescriptiveModel = Slots.atMostOne(owner, "prescriptiveModel", mapper.mapSlot("prescriptiveModel", owner, PrescriptiveModelUsage.class));
		predictiveModel = Slots.atMostOne(owner, "predictiveModel", mapper.mapSlot("predictiveModel", owner, PredictiveModelUsage.class));
		queryFlows = mapper.mapSlot("queryFlows", owner, QueryFlowUsage.class);
		descriptiveToPredictiveFlows = mapper.mapSlot("descriptiveToPredictiveFlows", owner, DescriptiveToPredictiveFlowUsage.class);
		descriptiveToPrescriptiveFlows = mapper.mapSlot("descriptiveToPrescriptiveFlows", owner, DescriptiveToPrescriptiveFlowUsage.class);
		predictiveToPrescriptiveFlows = mapper.mapSlot("predictiveToPrescriptiveFlows", owner, PredictiveToPrescriptiveFlowUsage.class);
		prescriptiveToPhysicalFlows = mapper.mapSlot("prescriptiveToPhysicalFlows", owner, PrescriptiveToPhysicalFlowUsage.class);
	}
}
