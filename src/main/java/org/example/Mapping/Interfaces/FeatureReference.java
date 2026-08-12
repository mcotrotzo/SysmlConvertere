package org.example.Mapping.Interfaces;
/**
 * Represents a reference to a feature in the model.
 */
public interface FeatureReference extends Expression {
	public Reference<? extends TwinAttribute> getTarget();

	/**
	 * Indicates whether this feature reference is the result of a query.
	 * @return true if it is the result of a query, false otherwise.
	 */
	boolean isResultOfQuery();
}
