package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

/** Library type ScalarValues::Boolean. */
@MappedLibrary(libraryName = "ScalarValues::Boolean", core = TwinAttributeCore.class)
public class TwinAttributeBooleanUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeBooleanDefinition> {
	public TwinAttributeBooleanUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
