package org.example.Mapping.Model.Function;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Function.FunctionCore;
import org.omg.sysml.lang.sysml.Behavior;
import org.omg.sysml.lang.sysml.Function;

/** Function of the standard library (+, DIV_real, ...). */
@MappedMetaClass(value = Function.class, core = FunctionCore.class)
public class BaseFunctionDefinition extends Model.Predefined.MetaClasses.Function.BaseFunctionDefinition {
	public BaseFunctionDefinition(Behavior sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
