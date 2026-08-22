package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
/**
 * Represents a custom type definition in the model.
 */
public interface CustomTypeDefinition extends TwinAttributeDefinition {

	/**
	 * Retrieves the list of fields associated with this custom type definition.
	 *
	 * @return a list of TwinAttribute objects representing the fields
	 */
	List<TwinAttributeUsage> getFields();
	/**
	 * When this definition is a child of another definition, this method returns the list of parent definitions.
	 *
	 * @return a list of references to parent CustomTypeDefinition objects
	 */
	List<Reference<? extends CustomTypeDefinition>> getParents();
}
