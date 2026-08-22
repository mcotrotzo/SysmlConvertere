package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigCalculationExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinCalculationExpression;
import org.omg.sysml.lang.sysml.InvocationExpression;

public abstract class ConfigCalculationExpressionMapped<E extends ConfigExpressionMapped<?>>
        extends TwinCalculationExpression<E>
        implements ConfigCalculationExpression<E> {
    protected ConfigCalculationExpressionMapped(InvocationExpression sysmlElement) {
        super(sysmlElement);
    }
}
