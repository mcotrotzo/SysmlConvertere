package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralInteger;

@MappedMetaclass
public class ConstLiteralIntegerExpressionMapped
        extends TwinLiteralIntegerExpression
        implements ConstLiteralExpression<Integer> {

    public ConstLiteralIntegerExpressionMapped(LiteralInteger sysmlElement) {
        super(sysmlElement);
    }
}
