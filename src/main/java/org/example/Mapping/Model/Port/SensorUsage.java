package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class SensorUsage extends TwinPortUsage<SensorCore, SensorDefinition> implements SensorCoreApi {
	public SensorUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new SensorCore(sysmlElement, mapper), mapper, SensorDefinition.class);
	}
}
