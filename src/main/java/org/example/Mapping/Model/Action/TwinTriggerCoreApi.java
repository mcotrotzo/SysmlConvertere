package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.Attribute.TwinAttributeBooleanUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.example.Mapping.Model.EnumAttribute.EnumTimeUnitUsage;
import org.example.Mapping.Model.Type.CoreApi;

public interface TwinTriggerCoreApi extends CoreApi<TwinTriggerCore> {
	default TwinAttributeIntegerUsage getInterval() { return getCore().getInterval(); }
	default EnumTimeUnitUsage getIntervalUnit() { return getCore().getIntervalUnit(); }
	default TwinAttributeBooleanUsage getTriggerOnly() { return getCore().getTriggerOnly(); }
}
