package org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements;

import lombok.Setter;
import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.Literal;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.TwinAction.MappedMetaclass;
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

