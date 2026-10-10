package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::Actuator", core = ActuatorCore.class)
public class ActuatorUsage extends TwinPortUsage<ActuatorCore, ActuatorDefinition> implements ActuatorCoreApi {
	public ActuatorUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
