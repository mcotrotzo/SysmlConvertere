package org.example.Mapping.NewVersion.TwinPort.Usage;

import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.omg.sysml.lang.sysml.Feature;

public class ActuatorUsageMapped extends ActuatorMapped<Feature> implements ActuatorUsage {
	public ActuatorUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
