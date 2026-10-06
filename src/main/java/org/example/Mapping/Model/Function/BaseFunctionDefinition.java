package org.example.Mapping.Model.Function;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Behavior;

/** Function of the standard library (+, DIV_real, ...). */
public class BaseFunctionDefinition extends FunctionDefinition<FunctionCore> {
	public BaseFunctionDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new FunctionCore(sysmlElement, mapper), mapper);
	}
}
