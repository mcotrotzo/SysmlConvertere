package org.example.Mapping.Interfaces;

public interface ForLoop extends Action {
	Model getLoopVariable();

	Expression getCollection();

	Action getBody();
}
