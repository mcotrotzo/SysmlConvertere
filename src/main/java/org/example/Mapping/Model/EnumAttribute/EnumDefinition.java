package org.example.Mapping.Model.EnumAttribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import Model.EmptyCore;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinEnumLibrary::TwinEnum", core = EmptyCore.class)
public class EnumDefinition extends Definition<EmptyCore, Classifier> {
	public EnumDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
