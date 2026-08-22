package org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalFeatureReferenceExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped.TwinFeatureChainExpression;
import org.omg.sysml.lang.sysml.FeatureChainExpression;

public abstract class LocalFeatureChainExpressionMapped<A extends TwinAttributeUsage>
        extends TwinFeatureChainExpression<A>
        implements LocalFeatureReferenceExpression<A> {
    protected LocalFeatureChainExpressionMapped(FeatureChainExpression sysmlElement) {
        super(sysmlElement);
    }
}
