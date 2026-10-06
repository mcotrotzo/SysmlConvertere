package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class TwinAttributeIntegerDefinition extends TwinAttributeDefinition<TwinAttributeCore> {
	public TwinAttributeIntegerDefinition(Classifier sysmlElement, Mapper newMappe) {
		super(sysmlElement, () -> new TwinAttributeCore(sysmlElement, newMappe), newMappe);
	}
}
