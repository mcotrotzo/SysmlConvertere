package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Definition;
import org.omg.sysml.lang.sysml.Classifier;

@ToString(callSuper = true)
public abstract class MappedElementDefinition<T extends Classifier> extends MappedElement<T> implements Definition {
	public MappedElementDefinition(T sysmlElement) {
		super(sysmlElement);
	}
}
