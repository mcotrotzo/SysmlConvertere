package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Action.ActionBlockCoreApi;
import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionCoreApi;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.EnumAttribute.EnumCustomStrategyTypeUsage;

public interface ExternalStrategyCoreApi extends TwinTriggerActionCoreApi<ExternalStrategyCore> {
	default TwinAttributeStringUsage getContentPath() { return getCore().getContentPath(); }
	default EnumCustomStrategyTypeUsage getStrategyType() { return getCore().getStrategyType(); }
}
