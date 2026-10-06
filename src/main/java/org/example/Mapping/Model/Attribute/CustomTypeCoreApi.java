package org.example.Mapping.Model.Attribute;

import java.util.List;

public interface CustomTypeCoreApi extends TwinAttributeCoreApi<CustomTypeCore> {
	default List<TwinAttributeUsage<?, ?>> getFields() { return getCore().getFields(); }
}
