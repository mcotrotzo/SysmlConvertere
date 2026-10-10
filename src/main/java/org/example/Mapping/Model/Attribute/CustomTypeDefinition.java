package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinActionLibrary::TwinCustomType", core = CustomTypeCore.class)
public class CustomTypeDefinition extends TwinAttributeDefinition<CustomTypeCore> implements CustomTypeCoreApi {
	public CustomTypeDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
