package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class CustomTypeUsage extends TwinAttributeUsage<CustomTypeCore, CustomTypeDefinition> implements CustomTypeCoreApi {
	public CustomTypeUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new CustomTypeCore(sysmlElement, mapper), mapper, CustomTypeDefinition.class);
	}
}
