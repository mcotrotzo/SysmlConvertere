package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;

import java.util.List;
/**
 * Represents a calculation action in the model. This is the USAGE of a function with arguments.
 */
public interface Calculation extends Expression {
	/**
	 * Returns the function that is being called in this calculation.
	 *
	 * @return the function being called
	 */
	Reference<? extends Function> getCalledFunction();

	/**
	 * Returns the list of arguments passed to the function in this calculation.
	 *
	 * @return the list of arguments. The order is important and corresponds to the order of parameters in the function definition from left to right.
	 */
	List<Expression> getArguments();
}
