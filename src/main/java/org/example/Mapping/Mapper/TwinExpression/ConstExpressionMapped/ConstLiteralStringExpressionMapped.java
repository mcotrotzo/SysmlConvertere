package org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralStringExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralString;

@MappedMetaclass
public class ConstLiteralStringExpressionMapped
        extends TwinLiteralStringExpression
        implements ConstLiteralExpression<String> {

    public ConstLiteralStringExpressionMapped(LiteralString sysmlElement) {
        super(sysmlElement);
    }
}
