package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.LiteralInteger;

public class TwinLiteralIntegerUsage extends TwinLiteralUsage<Integer> {
	public TwinLiteralIntegerUsage(LiteralInteger sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper, sysmlElement.getValue());
	}
}
