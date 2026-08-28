package org.example.Mapping.Interfaces.TwinAttribute.CustomType;

import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

public interface CustomType<T extends TypeKind> extends TwinAttribute<T> {

	/**
	 * Returns the fields that make up this custom type.
	 */
	CompartmentContainer<? extends TwinAttribute<Usage>> getFields();
}