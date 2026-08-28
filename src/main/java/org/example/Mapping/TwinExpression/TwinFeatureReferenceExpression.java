package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.TwinAttributeMapped.CustomTypeMapped.CustomAttributeMapped;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;
import org.omg.sysml.util.TypeUtil;

import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureReferenceExpression extends TwinExpression<FeatureReferenceExpression> implements FeatureReference {
	private MappedReference<? extends MappedElement<?, Usage>> target;

	public TwinFeatureReferenceExpression(FeatureReferenceExpression sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		var referent = getSysmlElement().getReferent();
		if (referent instanceof Feature feature) {
			feature.getType().forEach(type -> System.out.println("  " + type.path() + " [" + type.getClass().getSimpleName() + "]"));
			TypeUtil.getSupertypesOf(feature, true).forEach(type -> System.out.println("  " + type.path() + " [" + type.getClass().getSimpleName() + "]"));
		}
		target = context.mapReference(getSysmlElement().getReferent(), rawClassOf(MappedElement.class));

	}

	@Override
	public List<MappedReference<? extends Type<Usage>>> getChain() {
		return List.of(target);
	}
}
