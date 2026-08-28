package org.example.Mapping.NewVersion.Abstract;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public class CompartmentMapped<T extends MappedElement<?, Usage>>
		implements Compartment<T> {

	private final Model<?> parent;
	private final T element;
	private final boolean inherited;

	public CompartmentMapped(
			Model<?> parent,
			T element,
			boolean inherited
	) {
		this.parent = parent;
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

	@Override
	public Optional<? extends Model<?>> getParent() {
		return Optional.of(parent);
	}

	@Override
	public String getId() {
		return UUID.nameUUIDFromBytes(
				path().getBytes(StandardCharsets.UTF_8)
		).toString();
	}

	@Override
	public String getName() {
		return element.getName();
	}

	@Override
	public TypeKindNamespace getKind() {
		return Usage.INSTANCE;
	}

	@Override
	public String path() {
		return parent.path()
				+ ":compartment:"
				+ element.path();
	}

	@Override
	public String getDeterministicId() {
		return UUID.nameUUIDFromBytes(
				path().getBytes(StandardCharsets.UTF_8)
		).toString();
	}

	@Override
	public boolean isLibraryElement() {
		return parent.isLibraryElement();
	}
}