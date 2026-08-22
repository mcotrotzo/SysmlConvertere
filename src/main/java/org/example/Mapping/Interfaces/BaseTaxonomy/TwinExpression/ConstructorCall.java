package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.omg.sysml.lang.sysml.ConstructorExpression;

import java.util.List;


public interface ConstructorCall<E extends TwinExpression> extends TwinExpression {
    List<E> getArguments();

    Reference<? extends TwinAttributeDefinition> getConstructedType();
}
