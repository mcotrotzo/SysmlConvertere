package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ScalarValues::Integer", core = TwinAttributeCore.class)
public class TwinAttributeIntegerDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeIntegerDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
