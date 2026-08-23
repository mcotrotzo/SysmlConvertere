package org.example.Mapping.TwinAction.Definition;

import org.example.Mapping.Interfaces.TwinAction.Definition.ActionDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.Classifier;

@MappedMetaclass
public abstract class TwinActionDefinitionMapped <T extends Classifier> extends MappedElementDefinition<T> implements ActionDefinition {
	public TwinActionDefinitionMapped(T sysmlElement) {
		super(sysmlElement);
	}
}
