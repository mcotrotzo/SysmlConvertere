package org.example.Mapping.Interfaces;
/**
 * Represents a reference to a feature in the model.
 */
public interface FeatureReference extends Expression {
	public Reference<? extends TwinAttribute> getTarget();
}
