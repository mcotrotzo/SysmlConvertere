package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralStringExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralString;

@MappedMetaclass
public class ConfigLiteralStringExpressionMapped
        extends TwinLiteralStringExpression
        implements ConfigLiteralExpression<String> {

    public ConfigLiteralStringExpressionMapped(LiteralString sysmlElement) {
        super(sysmlElement);
    }
}
