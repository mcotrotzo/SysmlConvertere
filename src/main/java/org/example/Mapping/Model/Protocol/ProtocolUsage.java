package org.example.Mapping.Model.Protocol;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type CommunicationProtocol. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class ProtocolUsage<C extends ProtocolCore, D extends ProtocolDefinition> extends Usage<C, Feature, D> implements ProtocolCoreApi<C> {
	public ProtocolUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
