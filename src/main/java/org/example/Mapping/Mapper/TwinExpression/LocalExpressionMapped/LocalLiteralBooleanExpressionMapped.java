package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@MappedMetaclass
public class LocalLiteralBooleanExpressionMapped
        extends TwinLiteralBooleanExpression
        implements LocalLiteralExpression<Boolean> {

    public LocalLiteralBooleanExpressionMapped(LiteralBoolean sysmlElement) {
        super(sysmlElement);
    }
}
