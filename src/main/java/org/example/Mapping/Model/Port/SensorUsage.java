package org.example.Mapping.Model.Port;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::Sensor", core = SensorCore.class)
public class SensorUsage extends TwinPortUsage<SensorCore, SensorDefinition> implements SensorCoreApi {
	public SensorUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
