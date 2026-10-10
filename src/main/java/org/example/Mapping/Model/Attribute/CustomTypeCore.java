package org.example.Mapping.Model.Attribute;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Role;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;

public class CustomTypeCore extends TwinAttributeCore {
	@Getter private List<TwinAttributeUsage<?, ?>> fields = List.of();

	public CustomTypeCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractType<?, ?> owner) {
		fields = mapper.mapSlot("fields", owner, Slots.<TwinAttributeUsage<?, ?>>rawClassOf(TwinAttributeUsage.class));
		fields.forEach(attribute -> attribute.addRole(Role.CUSTOM_TYPE_MEMBER));
	}
}
