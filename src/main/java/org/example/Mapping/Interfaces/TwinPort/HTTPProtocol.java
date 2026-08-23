package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;

import java.util.List;

public interface HTTPProtocol extends Protocol {
	List<TwinBaseStringUsage> getUrl();
}
