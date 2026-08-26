package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinFunction.BaseFunction;
import org.example.Mapping.TwinFunction.FunctionMapped;
import org.omg.sysml.lang.sysml.CalculationDefinition;
import org.omg.sysml.lang.sysml.InvocationExpression;
import org.omg.sysml.lang.sysml.Type;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinCalculationExpression
		extends TwinInvocationExpression<InvocationExpression>
		implements Calculation {

	private Reference<? extends Function> invokeType;

	public TwinCalculationExpression(InvocationExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);

		Type instantiatedType =
				getSysmlElement().getInstantiatedType();

		MappedReference<BaseFunction> baseFunction = context.tryMapReference(
				instantiatedType,
				BaseFunction.class
		);

		if (baseFunction != null) {
			invokeType = baseFunction;
			return;
		}


		MappedReference<FunctionMapped> customFunction = context.tryMapReference(
				instantiatedType,
				FunctionMapped.class
		);

		if (customFunction != null) {
			invokeType = customFunction;
			return;
		}

		throw new MappingException(
				"Instantiated type '%s' is neither a base function nor a custom calculation."
						.formatted(instantiatedType.path())
		);
	}

	@Override
	public Reference<? extends Function> getCalledFunction() {
		return invokeType;
	}
}