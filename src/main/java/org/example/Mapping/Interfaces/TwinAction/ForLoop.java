package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface ForLoop extends Action<Usage> {

	Compartment<? extends TwinAttribute<Usage>> getLoopVariable();

	TwinExpression getCollection();

	Compartment<? extends Action<Usage>> getBody();
}