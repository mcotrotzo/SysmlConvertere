package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;


public interface ConstructorCall extends TwinExpression {
    List<? extends TwinExpression> getArguments();

    Reference<? extends TwinAttributeDefinition> getConstructedType();
}
