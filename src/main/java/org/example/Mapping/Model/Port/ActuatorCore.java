package org.example.Mapping.Model.Port;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Mapper;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class ActuatorCore extends TwinPortCore {
	@Getter private List<TwinAttributeUsage<?, ?>> commands = List.of();

	public ActuatorCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		super.fillSlots(owner);
		commands = mapper.mapSlot("commands", owner, Slots.<TwinAttributeUsage<?, ?>>rawClassOf(TwinAttributeUsage.class));
		commands.forEach(attribute -> attribute.addRole(Role.ACTUATOR));
	}
}
