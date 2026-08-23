package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;

public interface Calculation extends TwinExpression {
    Reference<? extends Function> getCalledFunction();

    List<? extends TwinExpression> getArguments();
}
