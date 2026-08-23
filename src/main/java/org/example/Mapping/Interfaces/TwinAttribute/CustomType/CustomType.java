package org.example.Mapping.Interfaces.TwinAttribute.CustomType;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;

public interface CustomType extends TwinAttribute {/**
	 * Returns the list of fields (twin attributes) that make up this custom type.
	 *
	 * @return a list of twin attributes representing the fields of the custom type
	 */
	List<TwinAttributeUsage> getFields();

}
