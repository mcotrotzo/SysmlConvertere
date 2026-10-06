package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;


public class TwinAttributeIntegerUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeIntegerDefinition> {
	public TwinAttributeIntegerUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, mapper), mapper, TwinAttributeIntegerDefinition.class);
	}
}
