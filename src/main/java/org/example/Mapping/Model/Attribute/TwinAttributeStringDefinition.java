package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ScalarValues::String", core = TwinAttributeCore.class)
public class TwinAttributeStringDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeStringDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
