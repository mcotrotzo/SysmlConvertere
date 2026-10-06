package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type ScalarValues::Boolean. */
public class TwinAttributeBooleanUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeBooleanDefinition> {
	public TwinAttributeBooleanUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, mapper), mapper, TwinAttributeBooleanDefinition.class);
	}
}
