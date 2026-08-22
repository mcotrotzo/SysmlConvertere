package org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConfigAttribute.Usage;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.AttributeWithExpression.TwinAttributeWithExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConfigAttribute.ConfigAttribute;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConfigExpression.ConfigExpression;

public interface ConfigAttributeUsage extends TwinAttributeWithExpression<ConfigExpression>, ConfigAttribute {
	@Override
	default Role getRole(){
		return Role.CONFIG;
	}
}
