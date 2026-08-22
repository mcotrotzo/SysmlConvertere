package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstCalculationExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinCalculationExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.InvocationExpression;

@MappedMetaclass
public class ConstCalculationExpressionMapped<T extends ConstExpressionMapped<?>>
        extends TwinCalculationExpression<T>
        implements ConstCalculationExpression<T> {
    public ConstCalculationExpressionMapped(InvocationExpression sysmlElement) {
        super(sysmlElement);
    }
}
