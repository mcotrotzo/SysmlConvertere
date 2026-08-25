package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.util.FeatureUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression> implements FeatureReference {

	private MappedReference<? extends TwinAttributeMapped<Usage>> target;

	public TwinFeatureChainExpression(FeatureChainExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		var targetFeature = FeatureUtil.getBasicFeatureOf(getSysmlElement().getTargetFeature());

		target = context.mapReference(targetFeature, TwinAttributeMapped.getRawUsageClass());

	}


	@Override
	public MappedReference<? extends TwinAttribute<Usage>> getTarget() {
		return target;
	}


}
