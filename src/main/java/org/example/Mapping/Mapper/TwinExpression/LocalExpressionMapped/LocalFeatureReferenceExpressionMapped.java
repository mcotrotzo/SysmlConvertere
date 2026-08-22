package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalFeatureReferenceExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinFeatureReferenceExpression;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

public abstract class LocalFeatureReferenceExpressionMapped<A extends TwinAttributeUsage>
        extends TwinFeatureReferenceExpression<A>
        implements LocalFeatureReferenceExpression<A> {
    protected LocalFeatureReferenceExpressionMapped(FeatureReferenceExpression sysmlElement) {
        super(sysmlElement);
    }
}
