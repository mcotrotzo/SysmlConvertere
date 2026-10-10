package org.example.Mapping.Model.Attribute;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Core.Core;
import org.omg.sysml.lang.sysml.Type;

public class TwinAttributeCore extends Core<Type> {

	public TwinAttributeCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType<?, ?> owner) {
	}
}
