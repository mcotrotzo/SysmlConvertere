package org.example.Mapping.Model.Database;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Type;

public class DatabaseCore extends Core<Type> {
	@Getter private TwinAttributeIntegerUsage durationInDays;

	public DatabaseCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		durationInDays = Slots.exactlyOne(owner, "durationInDays", mapper.mapSlot("durationInDays", owner, TwinAttributeIntegerUsage.class));
	}
}
