package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.List;

public interface ActuatorCoreApi extends TwinPortCoreApi<ActuatorCore> {
	default List<TwinAttributeUsage<?, ?>> getCommands() { return getCore().getCommands(); }
}
