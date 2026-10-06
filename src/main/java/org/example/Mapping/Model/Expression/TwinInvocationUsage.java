package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.InstantiationExpression;

import java.util.List;

/** Base of calculations and constructor calls: the arguments in call order. */
public abstract class TwinInvocationUsage<U extends InstantiationExpression> extends TwinExpressionUsage<U> {

	@Getter private List<TwinExpressionUsage> arguments = List.of();

	protected TwinInvocationUsage(U sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		arguments = Slots.mapAll(instance, this, sysmlElement.getArgument(), TwinExpressionUsage.class);
	}
}
