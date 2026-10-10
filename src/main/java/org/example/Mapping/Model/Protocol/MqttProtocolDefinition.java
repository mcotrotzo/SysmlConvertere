package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::MQTT_Protocol", core = MqttProtocolCore.class)
public class MqttProtocolDefinition extends ProtocolDefinition<MqttProtocolCore> implements MqttProtocolCoreApi {
	public MqttProtocolDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
