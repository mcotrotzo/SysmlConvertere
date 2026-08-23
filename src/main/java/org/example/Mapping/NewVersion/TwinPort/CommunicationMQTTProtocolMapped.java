package org.example.Mapping.NewVersion.TwinPort;


import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;
import org.example.Mapping.Interfaces.TwinPort.MQTTProtocol;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinStringMappedUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.MQTT_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationMQTTProtocolMapped extends CommunicationProtocolMapped implements MQTTProtocol {
	private List<TwinStringMappedUsage> topic = new ArrayList<>();
	private List<TwinStringMappedUsage> broker = new ArrayList<>();

	public CommunicationMQTTProtocolMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		topic = context.mapSlot(this, "topic", TwinStringMappedUsage.class);

		broker = context.mapSlot(this, "broker", TwinStringMappedUsage.class);
	}

	@Override
	public List<TwinBaseStringUsage> getTopic() {
		return new ArrayList<>(topic);
	}

	@Override
	public List<TwinBaseStringUsage> getBroker() {
		return new ArrayList<>(broker);
	}
}
