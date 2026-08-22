package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralRealExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralRational;

@MappedMetaclass
public class LocalLiteralRealExpressionMapped
        extends TwinLiteralRealExpression
        implements LocalLiteralExpression<Double> {

    public LocalLiteralRealExpressionMapped(LiteralRational sysmlElement) {
        super(sysmlElement);
    }
}
