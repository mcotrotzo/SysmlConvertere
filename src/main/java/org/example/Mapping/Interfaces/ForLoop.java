package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

public interface ForLoop extends Action {
	TwinAttributeUsage getLoopVariable();

	Expression getCollection();

	Action getBody();
}
