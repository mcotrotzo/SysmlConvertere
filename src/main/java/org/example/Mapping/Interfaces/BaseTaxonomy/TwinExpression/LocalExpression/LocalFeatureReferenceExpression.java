package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;

public interface LocalFeatureReferenceExpression<T extends TwinAttributeUsage>
        extends FeatureReference<T>, LocalExpression {
}
