package org.example.Mapping.Interfaces.Base.TypeKind;

import org.omg.sysml.lang.sysml.Element;

public final class NamespaceKind implements TypeKindNamespace {
	public static final NamespaceKind INSTANCE = new NamespaceKind();

	private NamespaceKind() {
	}

	@Override
	public Class<? extends Element> sysmlType() {
		return Element.class;
	}
}
