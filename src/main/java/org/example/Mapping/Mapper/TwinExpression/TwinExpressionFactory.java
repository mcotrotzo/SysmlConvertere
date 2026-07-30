package org.example.Mapping.Mapper.TwinExpression;

import org.example.Containers.ContainerManager;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;

import java.lang.reflect.Modifier;
import java.util.List;

public class TwinExpressionFactory {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public TwinExpression<?> create(Expression expression) {
        if (!(expression instanceof Expression)) {
            throw new RuntimeException();

        }

        ContainerManager containerManager = ContainerManager.getInstance();
        List<Class<TwinExpression<?>>> exp = containerManager.getTwinExpressionClasses();

        for (Class<TwinExpression<?>> twinExpressionClass : exp) {
            try {
                TwinExpression<?> twinExpression = twinExpressionClass.getDeclaredConstructor().newInstance();

                Class<?> expressionType = twinExpressionClass.getAnnotation(TwinExpressionAnnotation.class).value();

                System.out.println(twinExpression.getClass().getName());
                if (expressionType.isInstance(expression)) {
                    ((TwinExpression) twinExpression).parse((Expression) expression);
                    return twinExpression;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException();

    }
}