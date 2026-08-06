package org.example.Mapping.Interfaces;

import java.util.List;

public interface MQTTProtocol extends Protocol {
	List<TwinStringAttribute> getTopic();

	List<TwinStringAttribute> getBroker();
}
