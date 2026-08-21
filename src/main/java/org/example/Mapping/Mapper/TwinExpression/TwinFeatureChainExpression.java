package org.example.Mapping.Mapper.TwinExpression;

import lombok.ToString;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.util.FeatureUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression> implements FeatureReference {

	private MappedReference<TwinAttributeUsageMapped> target;

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
				TwinAttributeUsageMapped.class
		);
	}

	@Override
	public MappedReference<? extends TwinAttributeUsage> getTarget() {
		return target;
	}


	@Override
	public boolean isResultOfQuery() {
		return target.getReferent().getParent()
				.map(Query.class::isInstance)
				.orElse(false);
	}
}
