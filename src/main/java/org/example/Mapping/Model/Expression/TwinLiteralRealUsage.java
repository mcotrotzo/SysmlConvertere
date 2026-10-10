package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.LiteralRealUsage;
import org.omg.sysml.lang.sysml.LiteralRational;

@MappedMetaClass(value = LiteralRational.class, core = EmptyCore.class)
public class TwinLiteralRealUsage extends LiteralRealUsage {
	public TwinLiteralRealUsage(LiteralRational sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
