package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;


public class TwinAttributeStringUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeStringDefinition> {
	public TwinAttributeStringUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, mapper), mapper, TwinAttributeStringDefinition.class);
	}
}
