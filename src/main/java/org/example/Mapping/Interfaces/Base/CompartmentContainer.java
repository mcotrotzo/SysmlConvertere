package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

import java.util.List;

public interface CompartmentContainer<T extends Type<Usage>> {
	List<? extends Compartment<T>> getCompartment();

}
