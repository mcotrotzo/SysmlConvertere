package org.example.Mapping.Model;

import lombok.Getter;
import lombok.Setter;
import org.omg.sysml.lang.sysml.Type;


import java.util.Optional;
import java.util.UUID;

public abstract class AbstractModel<T extends Type> {

	protected Mapper instance;

	@Getter
	protected T sysmlElement;

	@Getter
	private final String name;

	@Getter
	@Setter
	private UUID id;

	@Getter
	@Setter
	protected boolean isInherited;

	@Getter
	private Optional<AbstractModel> parent = Optional.empty();




	public AbstractModel(T sysmlElement, Mapper mapper) {
		this.sysmlElement = sysmlElement;
		this.name = sysmlElement.getName();
		this.instance = mapper;

	}

	@Getter
	@Setter
	protected boolean isLibrary = false;

	public void setParent(AbstractModel<?> parent) {
		if (parent != null && this.parent.isEmpty()) {   // top-level elements have no parent
			this.parent = Optional.of(parent);
		}
	}

	public abstract void fillSlots();
}
