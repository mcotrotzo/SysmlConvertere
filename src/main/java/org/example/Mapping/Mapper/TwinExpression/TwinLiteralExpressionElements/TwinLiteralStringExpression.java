package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Data;
import lombok.ToString;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.omg.sysml.lang.sysml.LiteralString;

@TwinExpressionAnnotation(LiteralString.class)
@Data
@ToString(callSuper = true)
public class TwinLiteralStringExpression extends TwinLiteralExpression<String, LiteralString> {

    public TwinLiteralStringExpression() {
    }

    @Override
    public void parse(LiteralString expression) {
        this.setValue(expression.getValue());
    }
}
