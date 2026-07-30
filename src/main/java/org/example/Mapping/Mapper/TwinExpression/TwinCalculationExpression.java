package org.example.Mapping.Mapper.TwinExpression;

import lombok.Data;
import lombok.ToString;
import org.omg.sysml.lang.sysml.CalculationUsage;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.InvocationExpression;

import java.util.ArrayList;
import java.util.List;

@TwinExpressionAnnotation(InvocationExpression.class)
@Data
@ToString(callSuper = true)
public class TwinCalculationExpression extends TwinExpression<InvocationExpression> {
    private String calledFunction;
    private List<TwinExpression<?>> arguments = new ArrayList<>();
    private final TwinExpressionFactory twinExpressionFactory = new TwinExpressionFactory();

    @Override
    public void parse(InvocationExpression expression) {
        calledFunction = expression.getInstantiatedType() != null
                ? expression.getInstantiatedType().getName()
                : null;
        for (Expression arg : expression.getArgument()) {
            arguments.add(twinExpressionFactory.create(arg));
        }
    }
}