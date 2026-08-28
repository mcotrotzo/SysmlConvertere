package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

import java.util.List;

public interface Compartment<T extends Type<Usage>> {
	T getElement();
	boolean isInherited();
}
