package org.example.Mapping.Interfaces.Base.TypeKind;

import org.omg.sysml.lang.sysml.Element;

import java.util.stream.Stream;

public sealed interface TypeKindNamespace permits NamespaceKind, TypeKind, Expression {

	static TypeKindNamespace of(Element element) {
		return Stream.of(Usage.INSTANCE, Definition.INSTANCE, Expression.INSTANCE, NamespaceKind.INSTANCE).filter(kind -> kind.sysmlType().isInstance(element)).findFirst().orElseThrow(() -> new IllegalArgumentException("No TypeKindNamespace for: " + element.getClass().getName()));
	}

	Class<? extends Element> sysmlType();

}
