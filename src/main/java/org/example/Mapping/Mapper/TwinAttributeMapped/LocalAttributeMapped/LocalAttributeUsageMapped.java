package org.example.Mapping.Mapper.TwinAttributeMapped.LocalAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.LocalAttribute.Usage.LocalAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalExpression;
import org.example.Mapping.Mapper.TwinAttributeMapped.AttributeWithExpressionMapped.TwinAttributeWithExpressionMapped;
import org.example.Mapping.Mapper.TwinExpression.ConstExpressionMapped.ConstExpressionMapped;
import org.example.Mapping.Mapper.TwinExpression.LocalExpressionMapped.LocalExpressionMapped;
import org.omg.sysml.lang.sysml.Feature;
@ToString(callSuper = true)
public class LocalAttributeUsageMapped extends TwinAttributeWithExpressionMapped<LocalExpressionMapped<?>> implements LocalAttributeUsage {
	public LocalAttributeUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
