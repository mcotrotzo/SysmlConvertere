package org.example.Mapping.Model.Strategy;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.EnumAttribute.EnumCustomStrategyTypeUsage;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Type;

public class ExternalStrategyCore extends ActionBlockCore {
	@Getter private TwinAttributeStringUsage contentPath;
	@Getter private EnumCustomStrategyTypeUsage strategyType;

	public ExternalStrategyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		super.fillSlots(owner);
		contentPath = Slots.exactlyOne(owner, "contentPath", mapper.mapSlot("contentPath", owner, TwinAttributeStringUsage.class));
		strategyType = Slots.exactlyOne(owner, "strategyType", mapper.mapSlot("strategyType", owner, EnumCustomStrategyTypeUsage.class));
	}
}
