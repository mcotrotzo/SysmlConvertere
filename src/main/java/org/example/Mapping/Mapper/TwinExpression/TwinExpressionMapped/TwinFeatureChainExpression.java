package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped;

import lombok.ToString;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.Query;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.util.FeatureUtil;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinFeatureChainExpression<T extends TwinAttributeUsageMapped> extends TwinExpression<FeatureChainExpression> implements FeatureReference<T> {

	private MappedReference<T> target;

	public TwinFeatureChainExpression(FeatureChainExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		var targetFeature = FeatureUtil.getBasicFeatureOf(getSysmlElement().getTargetFeature());

		target = context.mapReference(targetFeature, resolveAttributeType());
	}

	@Override
	public MappedReference<T> getTarget() {
		return target;
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
