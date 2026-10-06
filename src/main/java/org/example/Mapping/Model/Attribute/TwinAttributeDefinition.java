package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

import java.util.function.Supplier;

public class TwinAttributeDefinition<C extends TwinAttributeCore> extends Definition<C, Classifier> implements TwinAttributeCoreApi<C> {
	public TwinAttributeDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
