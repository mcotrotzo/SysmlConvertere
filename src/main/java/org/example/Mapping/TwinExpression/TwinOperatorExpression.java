package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinFunction.FunctionMapped;
import org.omg.sysml.lang.sysml.OperatorExpression;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Optional;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinOperatorExpression extends TwinInvocationExpression<OperatorExpression> implements Calculation {
	public TwinOperatorExpression(OperatorExpression sysmlElement) {
		super(sysmlElement);
	}
	private Reference<? extends BaseFunction> invokeType;

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);

		Type instantiatedType = getSysmlElement().getInstantiatedType();

		if (instantiatedType == null) {
			throw new MappingException(
					"OperatorExpression '%s' has no instantiated type."
							.formatted(getSysmlElement().path())
			);
		}

		MappedReference<org.example.Mapping.TwinFunction.BaseFunction> baseFunction =
				context.tryMapReference(
						instantiatedType,
						org.example.Mapping.TwinFunction.BaseFunction.class
				);

		if (baseFunction != null) {
			invokeType = baseFunction;

			System.out.println(
					"Operator resolved to base function: "
							+ baseFunction.getReferent().path()
			);

			return;
		}



		throw new MappingException(
				"Instantiated type '%s' could not be mapped to a function."
						.formatted(instantiatedType.path())
		);
	}

	@Override
	public Reference<? extends BaseFunction> getCalledFunction() {
		return invokeType;
	}
}
