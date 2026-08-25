package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

public interface WhileLoop extends Action<Usage> {
	TwinExpression getCondition();

	TwinExpression getUntil();

	Action<Usage> getBody();
}
