package org.example.Mapping.Interfaces.Base.TypeKind;

import org.omg.sysml.lang.sysml.Element;

public final class Expression implements TypeKindNamespace {
	public static final Expression INSTANCE = new Expression();

	private Expression() {
	}

	@Override
	public Class<? extends Element> sysmlType() {
		return org.omg.sysml.lang.sysml.Expression.class;
	}
}
