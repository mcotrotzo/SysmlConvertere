package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@MappedMetaclass
@ToString(callSuper = true, onlyExplicitlyIncluded = false)
public class TwinFeatureReferenceExpression<T extends TwinAttributeUsageMapped> extends TwinExpression<FeatureReferenceExpression> implements FeatureReference<T> {
	private MappedReference<T> target;

	public TwinFeatureReferenceExpression(FeatureReferenceExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public MappedReference<T> getTarget() {
		return target;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		target = context.mapReference(getSysmlElement().getReferent(), resolveAttributeType());

	}

	@SuppressWarnings("unchecked")
	private Class<T> resolveAttributeType() {

		java.lang.reflect.Type genericSuperclass = getClass().getGenericSuperclass();

		if (!(genericSuperclass instanceof java.lang.reflect.ParameterizedType type)) {
			throw new IllegalStateException("Expected parameterized invocation expression superclass.");
		}

		java.lang.reflect.Type argument = type.getActualTypeArguments()[0];

		if (!(argument instanceof Class<?> clazz)) {
			throw new IllegalStateException("Expected concrete expression class, but got: " + argument);
		}

		return (Class<T>) clazz;

	}
}
