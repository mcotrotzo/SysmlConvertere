package org.example.Mapping.Interfaces.TwinPort;


import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;

import java.util.List;

public interface MQTTProtocol<T extends TypeKind> extends Protocol<T> {
	List<TwinBaseString<Usage>> getTopic();

	List<TwinBaseString<Usage>> getBroker();
}
