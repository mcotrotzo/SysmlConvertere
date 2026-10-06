package org.example.Mapping.Model.Flow;

import lombok.Getter;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.TwinActionUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyUsage;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FlowUsage;

import java.util.ArrayList;
import java.util.List;

public class TwinFlowUsage extends TwinActionUsage<EmptyCore, FlowUsage, TwinFlowDefinition> {

	@Getter private TwinAttributeUsage<?, ?> source;
	@Getter private TwinAttributeUsage<?, ?> target;

	public TwinFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, newMappe), newMappe, TwinFlowDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		Class<TwinAttributeUsage<?, ?>> attributeClass = Slots.rawClassOf(TwinAttributeUsage.class);
		List<Feature> ends = sysmlElement.getConnectorEnd();
		source = instance.mapChain(endChain(ends.get(0)), this, attributeClass);
		target = instance.mapChain(endChain(ends.get(1)), this, attributeClass);
	}

	public Class<? extends TaxonomyUsage> getSourceTaxonomy() {
		return TaxonomyUsage.class;
	}

	public Class<? extends TaxonomyUsage> getTargetTaxonomy() {
		return TaxonomyUsage.class;
	}

	private List<Feature> endChain(Feature end) {
		Feature referenced = end.getOwnedReferenceSubsetting().getReferencedFeature();
		List<Feature> chain = new ArrayList<>(referenced.getChainingFeature().isEmpty() ? List.of(referenced) : referenced.getChainingFeature());
		for (Feature owned : end.getOwnedFeature()) {
			owned.getOwnedRedefinition().stream().findFirst().ifPresent(r -> chain.add(r.getRedefinedFeature()));
		}
		return chain;
	}
}
