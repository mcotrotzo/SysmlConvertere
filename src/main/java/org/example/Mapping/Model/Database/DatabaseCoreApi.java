package org.example.Mapping.Model.Database;


import Model.Core.CoreApi;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
public interface DatabaseCoreApi<C extends DatabaseCore> extends CoreApi<C> {
	default TwinAttributeIntegerUsage getDurationInDays() { return getCore().getDurationInDays(); }
}
