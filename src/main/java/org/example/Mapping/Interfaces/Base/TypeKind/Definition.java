package org.example.Mapping.Interfaces.Base.TypeKind;

import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Element;

public final class Definition implements TypeKind {
	public static final Definition INSTANCE = new Definition();

	private Definition() {
	}

	@Override
	public Class<? extends Element> sysmlType() {
		return Classifier.class;
	}
}
