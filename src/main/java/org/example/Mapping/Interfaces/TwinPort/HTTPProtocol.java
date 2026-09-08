package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;

import java.util.List;

public interface HTTPProtocol<T extends TypeKind> extends Protocol<T> {
	Compartment<? extends TwinBaseString<Usage>> getUrl();
}
