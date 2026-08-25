package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@MappedMetaclass
@ToString(callSuper = true, onlyExplicitlyIncluded = false)
public class TwinFeatureReferenceExpression<T extends TwinAttributeMapped<Usage>> extends TwinExpression<FeatureReferenceExpression> implements FeatureReference {
	private MappedReference<? extends TwinAttributeMapped<Usage>> target;

	public TwinFeatureReferenceExpression(FeatureReferenceExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public MappedReference<? extends TwinAttribute<Usage>> getTarget() {
		return target;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		target = context.mapReference(getSysmlElement().getReferent(), TwinAttributeMapped.getRawUsageClass());
	}
}
