package org.example.Mapping.Interfaces;

import java.util.List;

public interface CollectionExpression extends Expression{
	Reference<? extends TwinTypeDefinition> getCollectionType();

	List<Expression> getArguments();
}
