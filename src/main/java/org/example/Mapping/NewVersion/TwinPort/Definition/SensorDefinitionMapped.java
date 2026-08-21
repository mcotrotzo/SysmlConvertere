package org.example.Mapping.NewVersion.TwinPort.Definition;

import org.example.Mapping.Interfaces.TwinPort.Definition.SensorDefinition;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.omg.sysml.lang.sysml.Definition;

public class SensorDefinitionMapped extends SensorMapped<Definition> implements SensorDefinition {
	public SensorDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
