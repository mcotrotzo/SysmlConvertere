package org.example.Mapping.TwinExpression.TwinLiteralExpressionElements;

import lombok.Setter;
import lombok.ToString;
import org.example.Mapping.Interfaces.TwinExpression.Literal;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.omg.sysml.lang.sysml.LiteralExpression;


@MappedMetaclass
@ToString(callSuper = true)
public abstract class TwinLiteralExpression<T, G extends LiteralExpression> extends TwinExpression<G> implements Literal<T> {

	@Setter
	private T value;

	public TwinLiteralExpression(G sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public T getLiteralValue() {
		return value;
	}
}

