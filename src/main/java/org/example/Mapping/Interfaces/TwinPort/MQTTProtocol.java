package org.example.Mapping.Interfaces.TwinPort;


import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;

import java.util.List;

public interface MQTTProtocol extends Protocol {
	List<TwinBaseStringUsage> getTopic();

	List<TwinBaseStringUsage> getBroker();
}
