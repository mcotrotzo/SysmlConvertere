package org.example.Mapping.Interfaces;
/**
 * Represents a feature chain in the model.For example p11.temp
 */
public interface FeatureChain extends Expression {

	Reference<? extends TwinAttribute> getTarget();
}
