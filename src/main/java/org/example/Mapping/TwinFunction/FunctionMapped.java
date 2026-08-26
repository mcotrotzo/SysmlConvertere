package org.example.Mapping.TwinFunction;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.omg.sysml.lang.sysml.ActionDefinition;
import org.omg.sysml.lang.sysml.CalculationDefinition;

public abstract class FunctionMapped extends TwinActionBlockMapped<CalculationDefinition, Definition> implements Function {

	public FunctionMapped(CalculationDefinition sysmlElement) {
		super(sysmlElement);
	}
}
