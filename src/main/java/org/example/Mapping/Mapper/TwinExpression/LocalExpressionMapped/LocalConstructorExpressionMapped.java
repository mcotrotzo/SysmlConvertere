package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalConstructorExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinConstructorExpression;
import org.omg.sysml.lang.sysml.ConstructorExpression;

public abstract class LocalConstructorExpressionMapped<E extends LocalExpression>
        extends TwinConstructorExpression<E>
        implements LocalConstructorExpression<E> {
    protected LocalConstructorExpressionMapped(ConstructorExpression sysmlElement) {
        super(sysmlElement);
    }
}
