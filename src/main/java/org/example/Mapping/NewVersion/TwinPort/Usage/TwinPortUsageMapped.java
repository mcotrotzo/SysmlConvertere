package org.example.Mapping.NewVersion.TwinPort.Usage;

import org.example.Mapping.Interfaces.TwinPort.Usage.TwinPortUsage;
import org.example.Mapping.NewVersion.TwinPort.TwinPortMapped;
import org.omg.sysml.lang.sysml.Feature;

public abstract class TwinPortUsageMapped extends TwinPortMapped<Feature> implements TwinPortUsage {
	public TwinPortUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
