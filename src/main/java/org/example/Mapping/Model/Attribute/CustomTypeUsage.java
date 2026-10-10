package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinActionLibrary::TwinCustomType", core = CustomTypeCore.class)
public class CustomTypeUsage extends TwinAttributeUsage<CustomTypeCore, CustomTypeDefinition> implements CustomTypeCoreApi {
	public CustomTypeUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
