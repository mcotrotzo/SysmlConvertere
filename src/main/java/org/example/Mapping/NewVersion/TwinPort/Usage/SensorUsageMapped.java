package org.example.Mapping.NewVersion.TwinPort.Usage;

import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.omg.sysml.lang.sysml.Feature;

public class SensorUsageMapped extends SensorMapped<Feature> implements SensorUsage {
	public SensorUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
