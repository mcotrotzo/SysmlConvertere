package org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigLiteralExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinLiteralExpressionElements.TwinLiteralRealExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.LiteralRational;

@MappedMetaclass
public class ConfigLiteralRealExpressionMapped
        extends TwinLiteralRealExpression
        implements ConfigLiteralExpression<Double> {

    public ConfigLiteralRealExpressionMapped(LiteralRational sysmlElement) {
        super(sysmlElement);
    }
}
