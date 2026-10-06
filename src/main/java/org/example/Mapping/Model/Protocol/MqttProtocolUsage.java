package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class MqttProtocolUsage extends ProtocolUsage<MqttProtocolCore, MqttProtocolDefinition> implements MqttProtocolCoreApi {
	public MqttProtocolUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new MqttProtocolCore(sysmlElement, mapper), mapper, MqttProtocolDefinition.class);
	}
}
