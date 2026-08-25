package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.Function;
import org.example.Mapping.Interfaces.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Calculation extends TwinExpression {
    Reference<? extends Function> getCalledFunction();

    List<? extends TwinExpression> getArguments();


}
