package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "ScalarValues::Real", core = TwinAttributeCore.class)
public class TwinAttributeRealUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeRealDefinition> {
	public TwinAttributeRealUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
