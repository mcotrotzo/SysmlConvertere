package org.example.Mapping.Mapper.TwinExpression;

import lombok.Data;
import org.omg.sysml.lang.sysml.Expression;

@Data
@TwinExpressionAnnotation(value = Expression.class)
public abstract class TwinExpression<T extends Expression> {



    public abstract void parse(T expression);

}

