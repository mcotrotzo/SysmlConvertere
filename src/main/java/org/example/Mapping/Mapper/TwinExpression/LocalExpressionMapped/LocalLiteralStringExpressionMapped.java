package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralStringExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralString;

@MappedMetaclass
public class LocalLiteralStringExpressionMapped
        extends TwinLiteralStringExpression
        implements LocalLiteralExpression<String> {

    public LocalLiteralStringExpressionMapped(LiteralString sysmlElement) {
        super(sysmlElement);
    }
}
