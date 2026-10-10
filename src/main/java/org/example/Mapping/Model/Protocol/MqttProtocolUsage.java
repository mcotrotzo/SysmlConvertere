package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::MQTT_Protocol", core = MqttProtocolCore.class)
public class MqttProtocolUsage extends ProtocolUsage<MqttProtocolCore, MqttProtocolDefinition> implements MqttProtocolCoreApi {
	public MqttProtocolUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
