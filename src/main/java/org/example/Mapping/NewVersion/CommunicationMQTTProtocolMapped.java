package org.example.Mapping.NewVersion;


import lombok.ToString;
import org.example.Mapping.Interfaces.MQTTProtocol;
import org.example.Mapping.Interfaces.TwinStringAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.MQTT_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationMQTTProtocolMapped extends CommunicationProtocolMapped implements MQTTProtocol {
	private List<TwinStringMappedUsage> topic = new ArrayList<>();
	private List<TwinStringMappedUsage> broker = new ArrayList<>();

	public CommunicationMQTTProtocolMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		topic = context.mapSlot(this, "topic", TwinStringMappedUsage.class);

		broker = context.mapSlot(this, "broker", TwinStringMappedUsage.class);
	}

	@Override
	public List<TwinStringAttributeUsage> getTopic() {
		return new ArrayList<>(topic);
	}

	@Override
	public List<TwinStringAttributeUsage> getBroker() {
		return new ArrayList<>(broker);
	}
}
