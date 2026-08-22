package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinExpression;
import org.omg.sysml.lang.sysml.Expression;

public abstract class LocalExpressionMapped<T extends Expression>
        extends TwinExpression<T>
        implements LocalExpression {
    protected LocalExpressionMapped(T sysmlElement) {
        super(sysmlElement);
    }
}
