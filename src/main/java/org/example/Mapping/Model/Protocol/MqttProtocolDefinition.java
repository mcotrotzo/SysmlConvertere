package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class MqttProtocolDefinition extends ProtocolDefinition<MqttProtocolCore> implements MqttProtocolCoreApi {
	public MqttProtocolDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new MqttProtocolCore(sysmlElement, mapper), mapper);
	}
}
