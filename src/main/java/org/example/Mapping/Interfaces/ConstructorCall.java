package org.example.Mapping.Interfaces;

import java.util.List;
/**
 * Represents a constructor call expression in the model.
 */
public interface ConstructorCall extends Expression {
	/**
	 * Returns the list of arguments passed to the constructor.
	 *
	 * @return a list of expressions representing the constructor arguments in order of left to right
	 */
	List<Expression> getArguments();

	/**
	 * Returns the constructed type of the constructor call, which is a reference to a TwinAttribute or a CustomTypeDefinition
	 *
	 * @return the constructed type of the constructor call
	 */
	Reference<? extends TwinTypeDefinition> getConstructedType();
}
