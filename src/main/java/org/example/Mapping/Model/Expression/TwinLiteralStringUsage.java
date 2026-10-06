package org.example.Mapping.Model.Expression;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.LiteralString;

public class TwinLiteralStringUsage extends TwinLiteralUsage<String> {
	public TwinLiteralStringUsage(LiteralString sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper, (sysmlElement).getValue());
	}
}
