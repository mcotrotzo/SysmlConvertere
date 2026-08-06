package org.example.Mapping.Mapper.TwinExpression;

import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Expression;


@MappedMetaclass
public abstract class TwinExpression<T extends Expression> extends MappedElement<T> implements org.example.Mapping.Interfaces.Expression {

	public TwinExpression(T sysmlElement) {
		super(sysmlElement);
	}

	public static Class<TwinExpression<?>> getRawClass() {
		return (Class<TwinExpression<?>>) (Class<?>) TwinExpression.class;

	}
}

