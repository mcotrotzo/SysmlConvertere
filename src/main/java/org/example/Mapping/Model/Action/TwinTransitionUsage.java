package org.example.Mapping.Model.Action;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.TransitionUsage;

import java.util.List;
import java.util.Optional;


public class TwinTransitionUsage extends TwinActionUsage<EmptyCore, TransitionUsage, TwinActionDefinition> {

	@Getter private List<TwinExpressionUsage> guard = List.of();
	@Getter private Optional<TwinActionUsage<?, ?, ?>> effectAction = Optional.empty();
	@Getter private TwinActionUsage<?, ?, ?> source;
	@Getter private TwinActionUsage<?, ?, ?> target;


	public TwinTransitionUsage(TransitionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, TwinActionDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		guard = Slots.mapAll(instance, this, sysmlElement.getGuardExpression(), TwinExpressionUsage.class);
		effectAction = Slots.atMostOne(this, "effectAction", Slots.mapAll(instance, this, sysmlElement.getEffectAction(),
				Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class)));
		source = instance.mapChain(List.of(sysmlElement.getSource()), this, TwinActionUsage.class);   // state before
		target = instance.mapChain(List.of(sysmlElement.getTarget()), this, TwinActionUsage.class);   // state after
	}

}
