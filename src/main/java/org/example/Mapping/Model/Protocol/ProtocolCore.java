package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.omg.sysml.lang.sysml.Type;

public class ProtocolCore extends Core<Type> {

	public ProtocolCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
	}
}
