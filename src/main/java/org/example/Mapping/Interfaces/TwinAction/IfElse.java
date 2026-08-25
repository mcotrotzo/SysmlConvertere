package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface IfElse extends Action<Usage> {
	TwinExpression getCondition();

	Action<Usage> getThenAction();

	Action<Usage> getElseAction();
}
