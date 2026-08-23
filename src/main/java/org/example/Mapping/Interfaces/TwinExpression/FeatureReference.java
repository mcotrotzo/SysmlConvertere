package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Reference;


public interface FeatureReference extends TwinExpression {
    Reference<? extends TwinAttributeUsage> getTarget();
}
