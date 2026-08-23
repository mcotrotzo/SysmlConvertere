package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
import java.util.Optional;

public interface TwinPort extends Type {
	Optional<Protocol> getProtocol();

	List<TwinAttributeUsage> getAttributes();

}
