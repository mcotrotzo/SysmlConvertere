package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralInteger;

@MappedMetaclass
public class LocalLiteralIntegerExpressionMapped
        extends TwinLiteralIntegerExpression
        implements LocalLiteralExpression<Integer> {

    public LocalLiteralIntegerExpressionMapped(LiteralInteger sysmlElement) {
        super(sysmlElement);
    }
}
