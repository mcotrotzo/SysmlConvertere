package org.example.Mapping.Model.Protocol;

import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;

public interface HttpProtocolCoreApi extends ProtocolCoreApi<HttpProtocolCore> {
	default TwinAttributeStringUsage getUrl() { return getCore().getUrl(); }
}
