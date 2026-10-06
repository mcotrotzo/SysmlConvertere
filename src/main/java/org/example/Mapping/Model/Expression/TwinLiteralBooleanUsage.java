package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.LiteralBoolean;

public class TwinLiteralBooleanUsage extends TwinLiteralUsage<Boolean> {
	public TwinLiteralBooleanUsage(LiteralBoolean sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper, sysmlElement.isValue());
	}
}
