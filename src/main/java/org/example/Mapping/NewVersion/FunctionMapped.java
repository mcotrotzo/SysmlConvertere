package org.example.Mapping.NewVersion;

import org.example.Mapping.Interfaces.Function;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;

public abstract class FunctionMapped<T extends org.omg.sysml.lang.sysml.Function> extends MappedElementDefinition<T> implements Function {


	public FunctionMapped(T sysmlElement) {
		super(sysmlElement);
	}
}
