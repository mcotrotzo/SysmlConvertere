package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralRealExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralRational;

@MappedMetaclass
public class ConstLiteralRealExpressionMapped
        extends TwinLiteralRealExpression
        implements ConstLiteralExpression<Double> {

    public ConstLiteralRealExpressionMapped(LiteralRational sysmlElement) {
        super(sysmlElement);
    }
}
