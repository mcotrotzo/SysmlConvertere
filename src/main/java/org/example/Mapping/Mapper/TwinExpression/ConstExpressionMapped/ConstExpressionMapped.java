package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinExpression;
import org.omg.sysml.lang.sysml.Expression;

public abstract class ConstExpressionMapped<T extends Expression>
        extends TwinExpression<T>
        implements ConstExpression {
    protected ConstExpressionMapped(T sysmlElement) {
        super(sysmlElement);
    }
}
