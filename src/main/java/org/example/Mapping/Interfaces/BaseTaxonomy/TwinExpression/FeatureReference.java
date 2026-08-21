package org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Reference;


public interface FeatureReference<T extends TwinAttributeUsage> extends TwinExpression {
    Reference<? extends T> getTarget();

    boolean isResultOfQuery();
}
