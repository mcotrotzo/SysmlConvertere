package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class HttpProtocolUsage extends ProtocolUsage<HttpProtocolCore, HttpProtocolDefinition> implements HttpProtocolCoreApi {
	public HttpProtocolUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new HttpProtocolCore(sysmlElement, mapper), mapper, HttpProtocolDefinition.class);
	}
}
