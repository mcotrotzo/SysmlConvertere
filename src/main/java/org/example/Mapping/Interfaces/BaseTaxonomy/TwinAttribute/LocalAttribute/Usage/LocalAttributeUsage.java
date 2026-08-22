package org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.LocalAttribute.Usage;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.AttributeWithExpression.TwinAttributeWithExpression;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.LocalAttribute.LocalAttribute;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.LocalExpression.LocalExpression;

public interface LocalAttributeUsage extends TwinAttributeWithExpression<LocalExpression>, LocalAttribute {
	@Override
	default Role getRole(){
		return Role.LOCAL;
	}
}
