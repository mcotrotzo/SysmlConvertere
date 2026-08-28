package org.example.Mapping.NewVersion.Abstract;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

public class CompartmentMapped<T extends MappedElement<?, Usage>>
		implements Compartment<T> {

	private final T element;
	private final boolean inherited;

	public CompartmentMapped(T element, boolean inherited) {
		this.element = element;
		this.inherited = inherited;
	}

	@Override
	public T getElement() {
		return element;
	}

	@Override
	public boolean isInherited() {
		return inherited;
	}
}