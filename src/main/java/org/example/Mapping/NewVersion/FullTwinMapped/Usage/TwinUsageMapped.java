package org.example.Mapping.NewVersion.FullTwinMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.FullTwin.TwinUsage;
import org.example.Mapping.NewVersion.FullTwinMapped.TwinMapped;
import org.omg.sysml.lang.sysml.Feature;

@ToString(callSuper = true)
public class TwinUsageMapped extends TwinMapped<Feature> implements TwinUsage {
	public TwinUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
