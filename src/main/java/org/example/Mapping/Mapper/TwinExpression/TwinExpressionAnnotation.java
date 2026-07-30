package org.example.Mapping.Mapper.TwinExpression;


import org.omg.sysml.lang.sysml.Expression;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TwinExpressionAnnotation {
    Class<? extends Expression> value();
}