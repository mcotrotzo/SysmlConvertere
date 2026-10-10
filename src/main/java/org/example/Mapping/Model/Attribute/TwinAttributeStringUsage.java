package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;


@MappedLibrary(libraryName = "ScalarValues::String", core = TwinAttributeCore.class)
public class TwinAttributeStringUsage extends TwinAttributeUsage<TwinAttributeCore, TwinAttributeStringDefinition> {
	public TwinAttributeStringUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
