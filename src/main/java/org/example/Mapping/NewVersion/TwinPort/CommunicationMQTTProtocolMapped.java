package org.example.Mapping.NewVersion.TwinPort;


import lombok.ToString;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinPort.MQTTProtocol;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeStringMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.MQTT_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationMQTTProtocolMapped<T extends TypeKind> extends CommunicationProtocolMapped<T> implements MQTTProtocol<T> {
	private CompartmentContainerMapped<TwinBaseAttributeStringMapped<Usage>> topic;
	private CompartmentContainerMapped<TwinBaseAttributeStringMapped<Usage>> broker;

	public CommunicationMQTTProtocolMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		topic = context.mapSlot(this, "topic", TwinBaseAttributeStringMapped.getRawStringUsageClass());

		broker = context.mapSlot(this, "broker", TwinBaseAttributeStringMapped.getRawStringUsageClass());
	}

	@Override
	public CompartmentContainer<? extends TwinBaseString<Usage>>  getTopic() {
		return topic;
	}

	@Override
	public CompartmentContainer<? extends TwinBaseString<Usage>> getBroker() {
		return broker;
	}
}
