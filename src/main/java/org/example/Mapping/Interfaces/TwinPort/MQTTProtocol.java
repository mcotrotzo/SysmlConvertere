package org.example.Mapping.Interfaces.TwinPort;


import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;

import java.util.List;

public interface MQTTProtocol<T extends TypeKind> extends Protocol<T> {
	CompartmentContainer<? extends TwinBaseString<Usage>> getTopic();

	CompartmentContainer<? extends TwinBaseString<Usage>> getBroker();
}
