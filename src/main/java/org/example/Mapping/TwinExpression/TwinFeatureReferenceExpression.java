package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@MappedMetaclass
@ToString(callSuper = true, onlyExplicitlyIncluded = false)
public abstract class TwinFeatureReferenceExpression<T extends TwinAttributeUsageMapped> extends TwinExpression<FeatureReferenceExpression> implements FeatureReference {
	private MappedReference<? extends TwinAttributeUsageMapped> target;

	public TwinFeatureReferenceExpression(FeatureReferenceExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public MappedReference<? extends TwinAttributeUsage> getTarget() {
		return target;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		target = context.mapReference(getSysmlElement().getReferent(), TwinAttributeUsageMapped.class);
	}
}
