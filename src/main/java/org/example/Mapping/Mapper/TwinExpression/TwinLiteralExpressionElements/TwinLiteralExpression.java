package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.omg.sysml.lang.sysml.*;

@EqualsAndHashCode(callSuper = true)
@Data
@TwinExpressionAnnotation(LiteralExpression.class)
public abstract class TwinLiteralExpression<T,G extends LiteralExpression> extends TwinExpression<G> {

    private T value;
    @Override
    public abstract void parse(G expression);
}

