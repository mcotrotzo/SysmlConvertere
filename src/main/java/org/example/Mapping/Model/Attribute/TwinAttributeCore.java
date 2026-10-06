package org.example.Mapping.Model.Attribute;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.omg.sysml.lang.sysml.Type;

public class TwinAttributeCore extends Core<Type> {

	public TwinAttributeCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
	}
}
