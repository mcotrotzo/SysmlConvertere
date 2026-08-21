package org.example.Mapping.NewVersion.TwinPort.Definition;

import org.example.Mapping.Interfaces.TwinPort.Definition.TwinPortDefinition;
import org.example.Mapping.Interfaces.TwinPort.TwinPort;
import org.example.Mapping.NewVersion.TwinPort.TwinPortMapped;
import org.omg.sysml.lang.sysml.Classifier;

public abstract class TwinPortDefinitionMapped extends TwinPortMapped<Classifier> implements TwinPortDefinition {
	public TwinPortDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
