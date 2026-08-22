package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigConstructorCallExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinConstructorExpression;
import org.omg.sysml.lang.sysml.ConstructorExpression;

public abstract class ConfigConstructorCallExpressionMapped<E extends ConfigExpressionMapped<?>>
        extends TwinConstructorExpression<E>
        implements ConfigConstructorCallExpression<E> {
    protected ConfigConstructorCallExpressionMapped(ConstructorExpression sysmlElement) {
        super(sysmlElement);
    }
}
