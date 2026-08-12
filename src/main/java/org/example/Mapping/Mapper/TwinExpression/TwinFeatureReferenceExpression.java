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
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@MappedMetaclass
@ToString(callSuper = true, onlyExplicitlyIncluded = false)
public class TwinFeatureReferenceExpression extends TwinExpression<FeatureReferenceExpression> implements FeatureReference {
	private MappedReference<TwinAttributeMapped> target;

	public TwinFeatureReferenceExpression(FeatureReferenceExpression sysmlElement) {
		super(sysmlElement);
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

	@Override
	public void parse(MappingContext context) throws MappingException {
		target = context.mapReference(getSysmlElement().getReferent(), TwinAttributeMapped.class);

	}
}
