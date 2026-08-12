package org.example.Mapping.Interfaces;

public interface ForLoop extends Action {
	TwinAttribute getLoopVariable();

	Expression getCollection();

	Action getBody();
}
