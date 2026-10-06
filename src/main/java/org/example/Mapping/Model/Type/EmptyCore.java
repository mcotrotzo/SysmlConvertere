package org.example.Mapping.Model.Type;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Type;

/** Core for concepts without shared definition/usage content (usage-only metaclasses, enums, flows). */
public class EmptyCore extends Core<Type> {

	public EmptyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
	}
}
