package org.example.Mapping.Mapper.TwinExpression;

import lombok.Data;
import lombok.ToString;
import org.eclipse.ocl.util.TypeUtil;
import org.example.Mapping.TwinAttributeRaw;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.util.FeatureUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@TwinExpressionAnnotation(FeatureChainExpression.class)
@Data
@ToString(callSuper = true)
public class TwinFeatureChainExpression extends TwinExpression<FeatureChainExpression>{

    private TwinAttributeRaw targetAttribute;
    @Override
    public void parse(FeatureChainExpression expression) {
        targetAttribute = new TwinAttributeRaw(expression.getTargetFeature());

    }
}
