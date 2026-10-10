package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.BooleanExpressionUsage;
import org.omg.sysml.lang.sysml.BooleanExpression;

@MappedMetaClass(value = BooleanExpression.class, core = EmptyCore.class)
public class TwinBooleanExpressionUsage extends BooleanExpressionUsage {
	public TwinBooleanExpressionUsage(BooleanExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
