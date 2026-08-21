package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression;

import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;

import java.util.List;


public interface ConstructorCall<E extends TwinExpression> extends TwinExpression {
    List<E> getArguments();

    Reference<? extends TwinTypeDefinition> getConstructedType();
}
