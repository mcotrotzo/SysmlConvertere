package org.example.Mapping.Model.Action;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Attribute.TwinAttributeBooleanUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.example.Mapping.Model.EnumAttribute.EnumTimeUnitUsage;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.omg.sysml.lang.sysml.Type;

public class TwinTriggerCore extends Core<Type> {
	@Getter private TwinAttributeIntegerUsage interval;
	@Getter private EnumTimeUnitUsage intervalUnit;
	@Getter private TwinAttributeBooleanUsage triggerOnly;

	public TwinTriggerCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		interval = mapper.mapSingleSlot("interval", owner, TwinAttributeIntegerUsage.class);
		intervalUnit = mapper.mapSingleSlot("intervalUnit", owner, EnumTimeUnitUsage.class);
		triggerOnly = mapper.mapSingleSlot("triggerOnly", owner, TwinAttributeBooleanUsage.class);
	}
}
