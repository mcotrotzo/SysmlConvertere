package org.example.Mapping.Mapper.TwinAttributeMapped.ConfigAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConfigAttribute.Usage.ConfigAttributeUsage;
import org.example.Mapping.Mapper.TwinAttributeMapped.AttributeWithExpressionMapped.TwinAttributeWithExpressionMapped;
import org.example.Mapping.Mapper.TwinExpression.ConfigExpressionMapped.ConfigExpressionMapped;
import org.omg.sysml.lang.sysml.Feature;
@ToString(callSuper = true)
public class ConfigUsageAttributeMapped
		extends TwinAttributeWithExpressionMapped<ConfigExpressionMapped<?>>
		implements ConfigAttributeUsage {

	public ConfigUsageAttributeMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}