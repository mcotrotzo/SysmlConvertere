package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Data;
import lombok.ToString;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.omg.sysml.lang.sysml.LiteralRational;

@TwinExpressionAnnotation(LiteralRational.class)
@Data
@ToString(callSuper = true)
public class TwinLiteralRealExpression extends TwinLiteralExpression<Double, LiteralRational> {

    public TwinLiteralRealExpression() {
    }

    @Override
    public void parse(LiteralRational expression) {
        this.setValue(expression.getValue());
    }
}
