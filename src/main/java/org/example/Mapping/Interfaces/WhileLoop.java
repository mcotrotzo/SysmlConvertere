package org.example.Mapping.Interfaces;

public interface WhileLoop extends Action {
	Expression getCondition();

	Expression getUntil();

	Action getBody();
}
