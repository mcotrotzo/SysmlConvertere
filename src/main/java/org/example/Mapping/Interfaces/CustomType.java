package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
/**
 * Represents twin attribute with a custom type as type in the model.
 */
public interface CustomType extends TwinAttributeUsage {

	/**
	 * Returns the list of fields (twin attributes) that make up this custom type.
	 *
	 * @return a list of twin attributes representing the fields of the custom type
	 */
	List<TwinAttributeUsage> getFields();
	/**
	 * Returns the definition of the custom type, which is a reference to a CustomTypeDefinition.
	 *
	 * @return the definition of the custom type
	 */
	Reference<? extends CustomTypeDefinition> getDefinition();
}
