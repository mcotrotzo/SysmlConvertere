package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Protocol.ProtocolUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.Type.CoreApi;

import java.util.Optional;

public interface TwinPortCoreApi<C extends TwinPortCore> extends CoreApi<C> {
	default Optional<ProtocolUsage<?, ?>> getProtocol() { return getCore().getProtocol(); }
	default TwinAttributeStringUsage getDeviceKey() { return getCore().getDeviceKey(); }
}
