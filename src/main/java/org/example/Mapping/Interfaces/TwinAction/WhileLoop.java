package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface WhileLoop extends ActionUsage {
	TwinExpression getCondition();

	TwinExpression getUntil();

	Action getBody();
}
