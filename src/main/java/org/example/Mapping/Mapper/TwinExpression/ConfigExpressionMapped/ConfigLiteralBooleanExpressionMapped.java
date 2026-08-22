package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralBoolean;

@MappedMetaclass
public class ConfigLiteralBooleanExpressionMapped
        extends TwinLiteralBooleanExpression
        implements ConfigLiteralExpression<Boolean> {

    public ConfigLiteralBooleanExpressionMapped(LiteralBoolean sysmlElement) {
        super(sysmlElement);
    }
}
