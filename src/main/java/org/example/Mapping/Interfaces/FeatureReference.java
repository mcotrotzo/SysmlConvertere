package org.example.Mapping.Interfaces;

public interface FeatureReference extends Expression {
	public Reference<? extends TwinAttribute> getTarget();
}
