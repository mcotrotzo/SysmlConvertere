package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;


public interface FeatureReference extends TwinExpression {
	Reference<? extends TwinAttribute<Usage>> getTarget();
}
