package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Data;
import lombok.ToString;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@TwinExpressionAnnotation(LiteralBoolean.class)
@Data
@ToString(callSuper = true)
public class TwinLiteralBooleanExpression extends TwinLiteralExpression<Boolean, LiteralBoolean> {

    public TwinLiteralBooleanExpression() {
    }

    @Override
    public void parse(LiteralBoolean expression) {
        this.setValue(expression.isValue());
    }
}
