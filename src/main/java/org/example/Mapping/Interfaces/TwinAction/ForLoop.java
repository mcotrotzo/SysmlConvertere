package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface ForLoop extends ActionUsage {
	TwinAttributeUsage getLoopVariable();

	TwinExpression getCollection();

	Action getBody();
}
