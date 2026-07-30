package org.example.Mapping.Mapper.TwinExpression;

import lombok.Data;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@Data
@TwinExpressionAnnotation(FeatureReferenceExpression.class)
class TwinFeatureReferenceExpression extends TwinExpression<FeatureReferenceExpression> {
    private String referencedFeatureName;

    public TwinFeatureReferenceExpression() {

    }

    @Override
    public void parse(FeatureReferenceExpression expression) {
        referencedFeatureName = expression.getReferent().getName();
    }
}
