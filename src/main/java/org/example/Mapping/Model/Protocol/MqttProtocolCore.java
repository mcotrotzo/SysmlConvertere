package org.example.Mapping.Model.Protocol;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Type;

public class MqttProtocolCore extends ProtocolCore {
	@Getter private TwinAttributeStringUsage broker;
	@Getter private TwinAttributeStringUsage topic;

	public MqttProtocolCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		broker = Slots.exactlyOne(owner, "broker", mapper.mapSlot("broker", owner, TwinAttributeStringUsage.class));
		topic = Slots.exactlyOne(owner, "topic", mapper.mapSlot("topic", owner, TwinAttributeStringUsage.class));
	}
}
