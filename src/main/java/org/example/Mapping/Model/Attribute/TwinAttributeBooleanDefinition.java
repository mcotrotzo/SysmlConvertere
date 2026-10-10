package org.example.Mapping.Model.Attribute;




import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ScalarValues::Boolean", core = TwinAttributeCore.class)
public class TwinAttributeBooleanDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeBooleanDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}
}
