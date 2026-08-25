package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinFunction.FunctionMapped;
import org.omg.sysml.lang.sysml.InvocationExpression;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinCalculationExpression extends TwinInvocationExpression<InvocationExpression, FunctionMapped<?>> implements Calculation {

	public TwinCalculationExpression(InvocationExpression sysmlElement) {
		super(sysmlElement);
	}

	@SuppressWarnings("unchecked")
	public static Class<FunctionMapped<?>> getFunctionRawClass() {
		return (Class<FunctionMapped<?>>) (Class<?>) FunctionMapped.class;
	}

	@Override
	protected Class<FunctionMapped<?>> resolveInvokeType() {
		return getFunctionRawClass();
	}

	@Override
	public Reference<? extends Function> getCalledFunction() {
		return getInvokeType();
	}
}