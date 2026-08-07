package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.CustomTypeMappedDefintion;

import java.util.List;

public interface CustomType extends TwinAttribute {

	List<TwinAttribute> getFields();
	Reference<? extends CustomTypeDefinition> getDefinition();
}
