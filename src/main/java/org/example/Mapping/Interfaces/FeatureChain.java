package org.example.Mapping.Interfaces;

public interface FeatureChain extends Expression {
	Reference<? extends TwinAttribute> getTarget();
}
