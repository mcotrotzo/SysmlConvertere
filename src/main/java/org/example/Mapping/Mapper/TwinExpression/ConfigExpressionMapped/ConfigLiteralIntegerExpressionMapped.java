package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralInteger;

@MappedMetaclass
public class ConfigLiteralIntegerExpressionMapped
        extends TwinLiteralIntegerExpression
        implements ConfigLiteralExpression<Integer> {

    public ConfigLiteralIntegerExpressionMapped(LiteralInteger sysmlElement) {
        super(sysmlElement);
    }
}
