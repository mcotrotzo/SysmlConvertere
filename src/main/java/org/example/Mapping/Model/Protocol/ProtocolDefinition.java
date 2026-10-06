package org.example.Mapping.Model.Protocol;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type CommunicationProtocol. Intermediate class: the concrete core is passed in by the subclass or the registry. */
public class ProtocolDefinition<C extends ProtocolCore> extends Definition<C, Classifier> implements ProtocolCoreApi<C> {
	public ProtocolDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
