package org.example.Mapping.Mapper.TwinExpression;

import lombok.ToString;

import org.example.Mapping.Interfaces.FeatureReference;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.impl.FeatureImpl;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression> implements FeatureReference {

	private MappedReference<TwinAttributeMapped> target;

	public TwinFeatureChainExpression(FeatureChainExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		var targetFeature = FeatureUtil.getBasicFeatureOf(
				getSysmlElement().getTargetFeature()
		);

		target = context.mapReference(
				targetFeature,
				TwinAttributeMapped.class
		);
	}

	@Override
	public MappedReference<? extends TwinAttribute> getTarget() {
		return target;
	}


	@Override
	public boolean isResultOfQuery() {
		return target.getReferent().getParent()
				.map(Query.class::isInstance)
				.orElse(false);
	}
}
