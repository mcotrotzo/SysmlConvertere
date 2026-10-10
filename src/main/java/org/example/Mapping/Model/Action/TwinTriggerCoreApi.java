package org.example.Mapping.Model.Action;


import Model.Core.CoreApi;
import org.example.Mapping.Model.Attribute.TwinAttributeBooleanUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeIntegerUsage;
import org.example.Mapping.Model.EnumAttribute.EnumTimeUnitUsage;
public interface TwinTriggerCoreApi extends CoreApi<TwinTriggerCore> {
	default TwinAttributeIntegerUsage getInterval() { return getCore().getInterval(); }
	default EnumTimeUnitUsage getIntervalUnit() { return getCore().getIntervalUnit(); }
	default TwinAttributeBooleanUsage getTriggerOnly() { return getCore().getTriggerOnly(); }
}
