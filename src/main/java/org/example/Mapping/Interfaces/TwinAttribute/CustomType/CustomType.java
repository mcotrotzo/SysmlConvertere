package org.example.Mapping.Interfaces.TwinAttribute.CustomType;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

import java.util.List;

public interface CustomType<T extends TypeKind> extends TwinAttribute<T> {/**
	 * Returns the list of fields (twin attributes) that make up this custom type.
	 *
	 * @return a list of twin attributes representing the fields of the custom type
	 */
	List<TwinAttribute<Usage>> getFields();

}
