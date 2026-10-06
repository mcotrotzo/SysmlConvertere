package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class CustomTypeDefinition extends TwinAttributeDefinition<CustomTypeCore> implements CustomTypeCoreApi {
	public CustomTypeDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new CustomTypeCore(sysmlElement, mapper), mapper);
	}
}
