package org.example.Mapping.Mapper.TwinAttributeMapped.ConstAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConstAttribute.Usage.ConstUsageAttribute;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstExpression.ConstExpression;
import org.example.Mapping.Mapper.TwinAttributeMapped.AttributeWithExpressionMapped.TwinAttributeWithExpressionMapped;
import org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped.ConstExpressionMapped;
import org.omg.sysml.lang.sysml.Feature;
@ToString(callSuper = true)
public class ConstUsageAttributeMapped
        extends TwinAttributeWithExpressionMapped<ConstExpressionMapped<?>>
        implements ConstUsageAttribute {

    public ConstUsageAttributeMapped(Feature sysmlElement) {
        super(sysmlElement);
    }
}
