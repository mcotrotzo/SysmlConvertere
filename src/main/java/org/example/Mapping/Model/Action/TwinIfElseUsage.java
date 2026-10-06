package org.example.Mapping.Model.Action;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.IfActionUsage;

import java.util.Optional;
import java.util.stream.Stream;


public class TwinIfElseUsage extends TwinActionUsage<EmptyCore, IfActionUsage, TwinActionDefinition> {

	@Getter private Optional<TwinExpressionUsage> condition = Optional.empty();
	@Getter private Optional<TwinActionUsage<?, ?, ?>> thenAction = Optional.empty();
	@Getter private Optional<TwinActionUsage<?, ?, ?>> elseAction = Optional.empty();

	public TwinIfElseUsage(IfActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, TwinActionDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		IfActionUsage ifAction = sysmlElement;
		condition = Slots.mapAll(instance, this, Stream.ofNullable(ifAction.getIfArgument()).toList(), TwinExpressionUsage.class).stream().findFirst();
		thenAction = Slots.mapAll(instance, this, Stream.ofNullable(ifAction.getThenAction()).toList(), Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class)).stream().findFirst();
		elseAction = Slots.mapAll(instance, this, Stream.ofNullable(ifAction.getElseAction()).toList(), Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class)).stream().findFirst();
	}
}
