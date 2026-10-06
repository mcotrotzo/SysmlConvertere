package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class TwinAttributeRealDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeRealDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, newMappe), newMappe);
	}
}
