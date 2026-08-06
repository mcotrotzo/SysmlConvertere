package org.example.Mapping.Interfaces;

import java.util.List;

public interface ConstructorCall extends Expression {
	List<Expression> getArguments();

	Reference<? extends TwinAttribute> getConstructedType();
}
