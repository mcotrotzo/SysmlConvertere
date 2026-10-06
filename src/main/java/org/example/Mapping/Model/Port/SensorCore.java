package org.example.Mapping.Model.Port;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Mapper;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class SensorCore extends TwinPortCore {
	@Getter private List<TwinAttributeUsage<?, ?>> measurements = List.of();

	public SensorCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		super.fillSlots(owner);
		measurements = mapper.mapSlot("measurements", owner, Slots.rawClassOf(TwinAttributeUsage.class));
		measurements.forEach(attribute -> attribute.addRole(Role.SENSOR));
	}
}
