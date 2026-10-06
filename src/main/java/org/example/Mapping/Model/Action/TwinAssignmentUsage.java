package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.*;

import java.util.ArrayList;
import java.util.List;

public class TwinAssignmentUsage extends TwinActionUsage<EmptyCore, AssignmentActionUsage, TwinActionDefinition> {

	@Getter
	private TwinAttributeUsage<?, ?> referent;
	@Getter
	private TwinExpressionUsage<?> value;

	public TwinAssignmentUsage(AssignmentActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, TwinActionDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();

		Expression ta = sysmlElement.getTargetArgument();
		List<Feature> chain = new ArrayList<>();
		if (ta instanceof FeatureReferenceExpression r) {
			chain.add(r.getReferent());
		}
		else
			if (ta instanceof FeatureChainExpression c) {
				if (c.getArgument().getFirst() instanceof FeatureReferenceExpression base) {
					chain.add(base.getReferent());
				}
				Feature t = c.getTargetFeature();
				chain.addAll(t.getChainingFeature().isEmpty() ? List.of(t) : t.getChainingFeature());
		}
		Feature ref = sysmlElement.getReferent();
		chain.addAll(ref.getChainingFeature().isEmpty() ? List.of(ref) : ref.getChainingFeature());
		referent = instance.mapChain(chain, this, TwinAttributeUsage.class);
		value = Slots.mapAll(instance, this, List.of(sysmlElement.getValueExpression()), TwinExpressionUsage.class).getFirst();
	}
}
