package org.example.Mapping.Model.Port;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Protocol.ProtocolUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.Slots;

import java.util.Optional;
import org.omg.sysml.lang.sysml.Type;

public class TwinPortCore extends Core<Type> {
	@Getter private Optional<ProtocolUsage<?, ?>> protocol = Optional.empty();
	@Getter private TwinAttributeStringUsage deviceKey;

	public TwinPortCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		protocol = Slots.atMostOne(owner, "communicationProtocol",
				mapper.mapSlot("communicationProtocol", owner, Slots.<ProtocolUsage<?, ?>>rawClassOf(ProtocolUsage.class)));
		deviceKey = Slots.exactlyOne(owner, "deviceKey", mapper.mapSlot("deviceKey", owner, TwinAttributeStringUsage.class));
	}
}
