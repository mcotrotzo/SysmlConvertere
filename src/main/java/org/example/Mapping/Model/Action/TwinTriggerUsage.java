package org.example.Mapping.Model.Action;


import Model.Annotation.MappedLibrary;
import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "TwinActionLibrary::TwinTrigger", core = TwinTriggerCore.class)
public class TwinTriggerUsage extends Usage<TwinTriggerCore, Feature, TwinTriggerDefinition> implements TwinTriggerCoreApi {
	public TwinTriggerUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
