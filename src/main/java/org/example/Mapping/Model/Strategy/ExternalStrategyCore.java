package org.example.Mapping.Model.Strategy;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.EnumAttribute.EnumCustomStrategyTypeUsage;
import org.omg.sysml.lang.sysml.Type;

public class ExternalStrategyCore extends TwinTriggerActionCore {
	@Getter private TwinAttributeStringUsage contentPath;
	@Getter private EnumCustomStrategyTypeUsage strategyType;

	public ExternalStrategyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		super.fillSlots(owner);
		contentPath = Slots.exactlyOne(owner, "contentPath", mapper.mapSlot("contentPath", owner, TwinAttributeStringUsage.class));
		strategyType = Slots.exactlyOne(owner, "strategyType", mapper.mapSlot("strategyType", owner, EnumCustomStrategyTypeUsage.class));
	}
}
