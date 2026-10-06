package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class TwinAttributeRealUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeRealDefinition> {
	public TwinAttributeRealUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, mapper), mapper, TwinAttributeRealDefinition.class);
	}
}
