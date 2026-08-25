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
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;
import org.omg.sysml.util.TypeUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureReferenceExpression extends TwinExpression<FeatureReferenceExpression> implements FeatureReference {
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
		var referent = getSysmlElement().getReferent();

		System.out.println("=== FEATURE REFERENCE ===");
		System.out.println("expression name = " + getSysmlElement().getName());
		System.out.println("expression class = " + getSysmlElement().getClass().getName());

		System.out.println("referent name = " + referent.getName());
		System.out.println("referent class = " + referent.getClass().getName());
		System.out.println("referent path = " + referent.path());

		System.out.println("referent owner = " + (referent.getOwner() == null ? "null" : referent.getOwner().path()));

		if (referent instanceof Feature feature) {
			System.out.println("referent types:");
			feature.getType().forEach(type -> System.out.println("  " + type.path() + " [" + type.getClass().getSimpleName() + "]"));

			System.out.println("referent supertypes:");
			TypeUtil.getSupertypesOf(feature, true).forEach(type -> System.out.println("  " + type.path() + " [" + type.getClass().getSimpleName() + "]"));
		}

		System.out.println("=========================");
		target = context.mapReference(getSysmlElement().getReferent(), TwinAttributeMapped.getRawUsageClass());

	}
}
