package org.example.Mapping.NewVersion.TwinPort.Definition;

import org.example.Mapping.Interfaces.TwinPort.Definition.ActuatorDefinition;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.omg.sysml.lang.sysml.Definition;

public class ActuatorDefinitionMapped extends ActuatorMapped<Definition> implements ActuatorDefinition {
	public ActuatorDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
