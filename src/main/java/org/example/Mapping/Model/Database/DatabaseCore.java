package org.example.Mapping.Model.Database;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Core.Core;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.omg.sysml.lang.sysml.Type;

public class DatabaseCore extends Core<Type> {
	@Getter private TwinAttributeIntegerUsage durationInDays;

	public DatabaseCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		durationInDays = Slots.exactlyOne(owner, "durationInDays", mapper.mapSlot("durationInDays", owner, TwinAttributeIntegerUsage.class));
	}
}
