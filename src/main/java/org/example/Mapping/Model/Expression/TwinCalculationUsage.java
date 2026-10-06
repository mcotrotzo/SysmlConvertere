package org.example.Mapping.Model.Expression;

import lombok.Getter;
import org.example.Mapping.Model.Function.FunctionDefinition;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.InvocationExpression;

/** Call of a base function (+, DIV_real, ...) or of a user calculation (calc def :> CustomCalculationAction). */
public class TwinCalculationUsage extends TwinInvocationUsage<InvocationExpression> {

	@Getter private FunctionDefinition<?> invokeType;

	public TwinCalculationUsage(InvocationExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		// definitions exist once: mapped without owner
		invokeType = instance.map(sysmlElement.getInstantiatedType(), null, FunctionDefinition.class);
	}
}
