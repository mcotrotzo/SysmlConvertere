package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ScalarValues::Real", core = TwinAttributeCore.class)
public class TwinAttributeRealDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeRealDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
