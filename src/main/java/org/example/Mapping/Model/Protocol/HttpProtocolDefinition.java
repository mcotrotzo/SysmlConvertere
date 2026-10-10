package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::HTTP_Protocol", core = HttpProtocolCore.class)
public class HttpProtocolDefinition extends ProtocolDefinition<HttpProtocolCore> implements HttpProtocolCoreApi {
	public HttpProtocolDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
