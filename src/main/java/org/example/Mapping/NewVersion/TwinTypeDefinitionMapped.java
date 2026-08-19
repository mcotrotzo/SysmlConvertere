package org.example.Mapping.NewVersion;

import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

public abstract class TwinTypeDefinitionMapped extends MappedElement<Classifier> implements TwinTypeDefinition {
	public TwinTypeDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
