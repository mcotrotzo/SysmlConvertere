package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@MappedMetaclass
public class ConstLiteralBooleanExpressionMapped
        extends TwinLiteralBooleanExpression
        implements ConstLiteralExpression<Boolean> {

    public ConstLiteralBooleanExpressionMapped(LiteralBoolean sysmlElement) {
        super(sysmlElement);
    }
}
