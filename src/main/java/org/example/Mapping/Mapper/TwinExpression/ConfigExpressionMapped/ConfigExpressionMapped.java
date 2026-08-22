package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinExpression;
import org.omg.sysml.lang.sysml.Expression;

public abstract class ConfigExpressionMapped<T extends Expression>
        extends TwinExpression<T>
        implements ConfigExpression {
    protected ConfigExpressionMapped(T sysmlElement) {
        super(sysmlElement);
    }
}
