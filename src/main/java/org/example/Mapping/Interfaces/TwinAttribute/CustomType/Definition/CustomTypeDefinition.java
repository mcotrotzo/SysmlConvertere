package org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition;

import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;

import java.util.List;
/**
 * Represents a custom type definition in the model.
 */
public interface CustomTypeDefinition extends TwinAttributeDefinition, CustomType {

	/**
	 * When this definition is a child of another definition, this method returns the list of parent definitions.
	 *
	 * @return a list of references to parent CustomTypeDefinition objects
	 */
	List<Reference<? extends CustomTypeDefinition>> getParents();
}
