package org.example.Mapping.NewVersion.Abstract;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

import java.util.ArrayList;
import java.util.List;

public class CompartmentContainerMapped<T extends MappedElement<?, Usage>>
		implements CompartmentContainer<T> {

	private List<CompartmentMapped<T>> compartments = new ArrayList<>();

	public CompartmentContainerMapped(
			List<CompartmentMapped<T>> compartments
	) {
		this.compartments = List.copyOf(compartments);
	}

	public CompartmentContainerMapped() {
	}

	public void addCompartment(CompartmentMapped<T> compartment) {
		compartments.add(compartment);
	}

	@Override
	public List<CompartmentMapped<T>> getCompartment() {
		return compartments;
	}
}