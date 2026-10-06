package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.util.ArrayList;
import java.util.List;


public class TwinFeatureChainUsage extends TwinReferenceUsage<FeatureChainExpression> {



	public TwinFeatureChainUsage(FeatureChainExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		List<Feature> chain = new ArrayList<>();
		if (sysmlElement.getArgument().getFirst() instanceof FeatureReferenceExpression base){
			chain.add(base.getReferent());
		}
		Feature t = sysmlElement.getTargetFeature();
		chain.addAll(t.getChainingFeature().isEmpty() ? List.of(t) : t.getChainingFeature());
		target = instance.mapChain(chain, this, Usage.class);
	}
}
