package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;
import java.util.Optional;


public interface ConstructorCall extends TwinExpression {
    List<? extends TwinExpression> getArguments();

    Reference<? extends TwinAttribute<Definition>> getConstructedType();

}
