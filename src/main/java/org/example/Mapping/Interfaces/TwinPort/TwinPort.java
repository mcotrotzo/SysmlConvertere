package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;

import java.util.Optional;

public interface TwinPort<T extends TypeKind> extends Type<T> {

	Optional<? extends Compartment<? extends Protocol<Usage>>> getProtocol();

	CompartmentContainer<? extends TwinAttribute<Usage>> getAttributes();
	Compartment<? extends TwinBaseString<Usage>> getDeviceKeyId();

}