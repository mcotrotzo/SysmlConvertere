package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface IfElse extends ActionUsage {
	TwinExpression getCondition();

	Action getThenAction();

	Action getElseAction();
}
