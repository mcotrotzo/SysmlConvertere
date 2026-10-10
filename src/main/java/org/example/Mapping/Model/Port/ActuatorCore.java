package org.example.Mapping.Model.Port;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class ActuatorCore extends TwinPortCore {
	@Getter private List<TwinAttributeUsage<?, ?>> commands = List.of();

	public ActuatorCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractType<?, ?> owner) {
		super.fillSlots(owner);
		commands = mapper.mapSlot("commands", owner, Slots.<TwinAttributeUsage<?, ?>>rawClassOf(TwinAttributeUsage.class));
		commands.forEach(attribute -> attribute.addRole(Role.ACTUATOR));
	}
}
