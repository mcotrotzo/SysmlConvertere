package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::Actuator", core = ActuatorCore.class)
public class ActuatorDefinition extends TwinPortDefinition<ActuatorCore> implements ActuatorCoreApi {
	public ActuatorDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
