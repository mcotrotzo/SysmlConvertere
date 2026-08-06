package org.example.Mapping.NewVersion;

import org.example.Mapping.Interfaces.Function;
import org.example.Mapping.NewVersion.Abstract.MappedElement;

public abstract class FunctionMapped<T extends org.omg.sysml.lang.sysml.Function> extends MappedElement<T> implements Function {


	public FunctionMapped(T sysmlElement) {
		super(sysmlElement);
	}
}
