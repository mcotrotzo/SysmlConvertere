package org.example.Mapping.Interfaces.TwinAttribute.CustomType.Usage;

import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomTypeDefinition;

import java.util.List;
/**
 * Represents twin attribute with a custom type as type in the model.
 */
public interface CustomTypeUsage extends TwinAttributeUsage, CustomType {

	/**
	 * Returns the definition of the custom type, which is a reference to a CustomTypeDefinition.
	 *
	 * @return the definition of the custom type
	 */
	Reference<? extends CustomTypeDefinition> getDefinition();
}
