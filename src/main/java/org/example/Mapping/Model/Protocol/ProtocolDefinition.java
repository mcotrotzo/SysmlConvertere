package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type CommunicationProtocol. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "PhysicalTwinLibrary::CommunicationProtocol", core = ProtocolCore.class)
public class ProtocolDefinition<C extends ProtocolCore> extends Definition<C, Classifier> implements ProtocolCoreApi<C> {
	public ProtocolDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
