package org.example.Mapping.Model.Action;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;

import java.util.Optional;
import java.util.stream.Stream;


public class TwinWhileUsage extends TwinActionUsage<EmptyCore, WhileLoopActionUsage, TwinActionDefinition> {

	@Getter private Optional<TwinExpressionUsage> condition = Optional.empty();
	@Getter private Optional<TwinExpressionUsage> until = Optional.empty();
	@Getter private Optional<TwinActionUsage<?, ?, ?>> body = Optional.empty();

	public TwinWhileUsage(WhileLoopActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, TwinActionDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		WhileLoopActionUsage loop = sysmlElement;
		condition = Slots.mapAll(instance, this, Stream.ofNullable(loop.getWhileArgument()).toList(), TwinExpressionUsage.class).stream().findFirst();
		until = Slots.mapAll(instance, this, Stream.ofNullable(loop.getUntilArgument()).toList(), TwinExpressionUsage.class).stream().findFirst();
		body = Slots.mapAll(instance, this, Stream.ofNullable(loop.getBodyAction()).toList(), Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class)).stream().findFirst();
	}
}
