package org.example.Mapping.Model.Protocol;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Core.Core;
import org.omg.sysml.lang.sysml.Type;

public class ProtocolCore extends Core<Type> {

	public ProtocolCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
	}
}
