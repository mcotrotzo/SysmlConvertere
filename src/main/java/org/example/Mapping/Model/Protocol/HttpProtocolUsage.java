package org.example.Mapping.Model.Protocol;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "PhysicalTwinLibrary::HTTP_Protocol", core = HttpProtocolCore.class)
public class HttpProtocolUsage extends ProtocolUsage<HttpProtocolCore, HttpProtocolDefinition> implements HttpProtocolCoreApi {
	public HttpProtocolUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
