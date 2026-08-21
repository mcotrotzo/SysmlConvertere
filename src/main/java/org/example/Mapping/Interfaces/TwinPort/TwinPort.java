package org.example.Mapping.Interfaces.TwinPort;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.PhysicalContext;
import org.example.Mapping.Interfaces.Protocol;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
import java.util.Optional;

public interface TwinPort extends PhysicalContext, Type {
	Optional<Protocol> getProtocol();

	List<TwinAttributeUsage> getAttributes();

}
