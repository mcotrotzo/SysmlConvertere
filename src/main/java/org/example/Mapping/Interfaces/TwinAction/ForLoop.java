package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface ForLoop extends Action<Usage> {
	TwinAttribute<Usage> getLoopVariable();

	TwinExpression getCollection();

	Action<Usage> getBody();
}
