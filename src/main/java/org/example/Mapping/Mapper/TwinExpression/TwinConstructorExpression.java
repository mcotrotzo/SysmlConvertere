package org.example.Mapping.Mapper.TwinExpression;

import lombok.Data;
import lombok.ToString;
import org.omg.sysml.lang.sysml.ConstructorExpression;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@TwinExpressionAnnotation(ConstructorExpression.class)
@Data
@ToString(callSuper=true)
public class TwinConstructorExpression extends TwinExpression<ConstructorExpression> {

    List<TwinExpression> arguments = new ArrayList<>();
    Type type;

    @Override
    public void parse(ConstructorExpression expression) {
        TwinExpressionFactory twinExpressionFactory = new TwinExpressionFactory();
        for(Expression arg : expression.getArgument()) {
            TwinExpression twinArg = twinExpressionFactory.create(arg);
            arguments.add(twinArg);
        }

        type = expression.instantiatedType();
    }
}
