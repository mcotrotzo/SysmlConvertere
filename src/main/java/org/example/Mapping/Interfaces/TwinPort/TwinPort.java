package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

import java.util.List;
import java.util.Optional;

public interface TwinPort<T extends TypeKind> extends Type<T> {
	Optional<Protocol<Usage>> getProtocol();

	List<TwinAttribute<Usage>> getAttributes();

}
