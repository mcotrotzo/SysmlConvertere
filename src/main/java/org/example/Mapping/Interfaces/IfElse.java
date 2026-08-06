package org.example.Mapping.Interfaces;

public interface IfElse extends Action {
	Expression getCondition();

	Action getThenAction();

	Action getElseAction();
}
