package org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.AttributeWithExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.TwinExpression;

public interface TwinAttributeWithExpression<T extends TwinExpression> extends TwinAttributeUsage {
    T getExpression();
}
