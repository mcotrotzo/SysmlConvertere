package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type CommunicationProtocol. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "PhysicalTwinLibrary::CommunicationProtocol", core = ProtocolCore.class)
public class ProtocolUsage<C extends ProtocolCore, D extends ProtocolDefinition<?>> extends Usage<C, Feature, D> implements ProtocolCoreApi<C> {
	public ProtocolUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
