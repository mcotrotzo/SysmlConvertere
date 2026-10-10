package org.example.Mapping.Model.Port;


import Model.Core.CoreApi;
import org.example.Mapping.Model.Protocol.ProtocolUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import java.util.Optional;

public interface TwinPortCoreApi<C extends TwinPortCore> extends CoreApi<C> {
	default Optional<ProtocolUsage<?, ?>> getProtocol() { return getCore().getProtocol(); }
	default TwinAttributeStringUsage getDeviceKey() { return getCore().getDeviceKey(); }
}
