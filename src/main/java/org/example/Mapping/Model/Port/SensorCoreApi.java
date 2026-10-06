package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.List;

public interface SensorCoreApi extends TwinPortCoreApi<SensorCore> {
	default List<TwinAttributeUsage<?, ?>> getMeasurements() { return getCore().getMeasurements(); }
}
