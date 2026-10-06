package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Role;
import lombok.Getter;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Type.EmptyCore;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.Optional;
import java.util.stream.Stream;

public class TwinForLoopUsage extends TwinActionUsage<EmptyCore, ForLoopActionUsage, TwinActionDefinition> {

	@Getter private Optional<TwinAttributeUsage<?, ?>> loopVariable = Optional.empty();
	@Getter private Optional<TwinExpressionUsage> collection = Optional.empty();
	@Getter private Optional<TwinActionUsage<?, ?, ?>> body = Optional.empty();

	public TwinForLoopUsage(ForLoopActionUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, newMappe), newMappe, TwinActionDefinition.class);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		ForLoopActionUsage loop = sysmlElement;
		loopVariable = Slots.mapAll(instance, this, Stream.ofNullable(loop.getLoopVariable()).toList(), Slots.<TwinAttributeUsage<?, ?>>rawClassOf(TwinAttributeUsage.class)).stream().findFirst();
		loopVariable.ifPresent(variable -> variable.addRole(Role.FOR_LOOP_VARIABLE));
		collection = Slots.mapAll(instance, this, Stream.ofNullable(loop.getSeqArgument()).toList(), TwinExpressionUsage.class).stream().findFirst();
		body = Slots.mapAll(instance, this, Stream.ofNullable(loop.getBodyAction()).toList(), Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class)).stream().findFirst();
	}
}
