package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Data;
import lombok.ToString;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.omg.sysml.lang.sysml.LiteralInteger;

@TwinExpressionAnnotation(LiteralInteger.class)
@Data
@ToString(callSuper = true)
public class TwinLiteralIntegerExpression extends TwinLiteralExpression<Integer, LiteralInteger> {

    public TwinLiteralIntegerExpression() {
    }

    @Override
    public void parse(LiteralInteger expression) {
        this.setValue(expression.getValue());
    }
}
