package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.LiteralRational;

public class TwinLiteralRealUsage extends TwinLiteralUsage<Double> {
	public TwinLiteralRealUsage(LiteralRational sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper, sysmlElement.getValue());
	}
}
