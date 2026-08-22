package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalCalculationExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinCalculationExpression;
import org.omg.sysml.lang.sysml.InvocationExpression;

public abstract class LocalCalculationExpressionMapped<E extends LocalExpression>
        extends TwinCalculationExpression<E>
        implements LocalCalculationExpression<E> {
    protected LocalCalculationExpressionMapped(InvocationExpression sysmlElement) {
        super(sysmlElement);
    }
}
