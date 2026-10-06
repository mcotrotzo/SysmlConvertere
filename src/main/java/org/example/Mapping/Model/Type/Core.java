package org.example.Mapping.Model.Type;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Type;

public abstract class Core<T extends Type> {

	protected T sysmlElement;
	protected Mapper mapper;

	public Core(T sysmlElement, Mapper mapper) {
		this.sysmlElement = sysmlElement; this.mapper = mapper;
	}

	public abstract void fillSlots(AbstractModel<?> owner);

}
