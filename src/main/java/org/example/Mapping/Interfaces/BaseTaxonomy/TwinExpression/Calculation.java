package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression;

import org.example.Mapping.Interfaces.Function;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;

public interface Calculation<E extends TwinExpression> extends TwinExpression {
    Reference<? extends Function> getCalledFunction();

    List<E> getArguments();
}
