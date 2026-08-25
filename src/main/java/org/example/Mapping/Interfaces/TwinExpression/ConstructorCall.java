package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

import java.util.List;


public interface ConstructorCall extends TwinExpression {
	List<? extends TwinExpression> getArguments();

	Reference<? extends TwinAttribute<Definition>> getConstructedType();

}
