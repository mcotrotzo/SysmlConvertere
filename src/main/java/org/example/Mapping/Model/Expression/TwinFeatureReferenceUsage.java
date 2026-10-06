package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

import java.util.List;


public class TwinFeatureReferenceUsage extends TwinReferenceUsage<FeatureReferenceExpression> {



	public TwinFeatureReferenceUsage(FeatureReferenceExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		target = instance.mapChain(List.of(sysmlElement.getReferent()), this, Usage.class);
	}

}
