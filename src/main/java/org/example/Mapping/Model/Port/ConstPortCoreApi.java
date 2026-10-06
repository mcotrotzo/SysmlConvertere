package org.example.Mapping.Model.Port;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;

import java.util.List;

public interface ConstPortCoreApi extends TwinPortCoreApi<ConstPortCore> {
	default List<TwinAttributeUsage<?, ?>> getMeasurements() { return getCore().getMeasurements(); }
}
