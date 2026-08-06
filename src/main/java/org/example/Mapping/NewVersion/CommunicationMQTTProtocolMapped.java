package org.example.Mapping.NewVersion;


import lombok.ToString;
import org.example.Mapping.Interfaces.MQTTProtocol;
import org.example.Mapping.Interfaces.TwinStringAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.MQTT_PROTOCOL)
@ToString(callSuper = true)
public class CommunicationMQTTProtocolMapped extends CommunicationProtocolMapped implements MQTTProtocol {
	private Set<TwinStringMapped> topic = new HashSet<>();
	private Set<TwinStringMapped> broker = new HashSet<>();

	public CommunicationMQTTProtocolMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		topic = new HashSet<>(context.mapSlot(this, "topic", TwinStringMapped.class));

		broker = new HashSet<>(context.mapSlot(this, "broker", TwinStringMapped.class));
	}

	@Override
	public List<TwinStringAttribute> getTopic() {
		return new ArrayList<>(topic);
	}

	@Override
	public List<TwinStringAttribute> getBroker() {
		return new ArrayList<>(broker);
	}
}
