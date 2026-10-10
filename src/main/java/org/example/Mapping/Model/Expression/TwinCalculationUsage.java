package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.CalculationUsage;
import org.omg.sysml.lang.sysml.InvocationExpression;

@MappedMetaClass(value = InvocationExpression.class, core = EmptyCore.class)
public class TwinCalculationUsage extends CalculationUsage {
	public TwinCalculationUsage(InvocationExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
