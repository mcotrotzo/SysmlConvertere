package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "TwinActionLibrary::TwinTrigger", core = TwinTriggerCore.class)
public class TwinTriggerDefinition extends Definition<TwinTriggerCore, Classifier> implements TwinTriggerCoreApi {
	public TwinTriggerDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
