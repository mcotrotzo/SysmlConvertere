package org.example.Mapping.Model.Expression;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.LiteralExpression;

public abstract class TwinLiteralUsage<V> extends TwinExpressionUsage<LiteralExpression> {

	@Getter private final V value;
	protected TwinLiteralUsage(LiteralExpression sysmlElement, Mapper mapper, V value) {
		super(sysmlElement, mapper);
		this.value = value;
	}
}
