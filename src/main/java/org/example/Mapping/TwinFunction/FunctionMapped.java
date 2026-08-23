package org.example.Mapping.TwinFunction;

import org.example.Mapping.Interfaces.TwinAction.Definition.ActionDefinition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.TwinAction.Definition.TwinActionDefinitionMapped;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;

public abstract class FunctionMapped<T extends org.omg.sysml.lang.sysml.Function> extends TwinActionBlockMapped<T> implements Function {


	public FunctionMapped(T sysmlElement) {
		super(sysmlElement);
	}
}
