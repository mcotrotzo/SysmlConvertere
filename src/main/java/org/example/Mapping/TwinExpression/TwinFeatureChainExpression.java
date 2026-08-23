package org.example.Mapping.TwinExpression;

import lombok.ToString;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.util.FeatureUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression> implements FeatureReference {

	private MappedReference<? extends TwinAttributeUsageMapped> target;

	public TwinFeatureChainExpression(FeatureChainExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		var targetFeature = FeatureUtil.getBasicFeatureOf(getSysmlElement().getTargetFeature());

		target = context.mapReference(targetFeature, TwinAttributeUsageMapped.class);

	}



	@Override
	public MappedReference<? extends TwinAttributeUsage> getTarget() {
		return target;
	}


}
