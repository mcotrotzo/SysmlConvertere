package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;

public interface MqttProtocolCoreApi extends ProtocolCoreApi<MqttProtocolCore> {
	default TwinAttributeStringUsage getBroker() { return getCore().getBroker(); }
	default TwinAttributeStringUsage getTopic() { return getCore().getTopic(); }
}
