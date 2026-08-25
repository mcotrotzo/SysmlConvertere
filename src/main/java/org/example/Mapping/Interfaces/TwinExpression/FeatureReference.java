package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;


public interface FeatureReference extends TwinExpression {
    Reference<? extends TwinAttribute<Usage>> getTarget();
}
