package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class HttpProtocolDefinition extends ProtocolDefinition<HttpProtocolCore> implements HttpProtocolCoreApi {
	public HttpProtocolDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new HttpProtocolCore(sysmlElement, mapper), mapper);
	}
}
