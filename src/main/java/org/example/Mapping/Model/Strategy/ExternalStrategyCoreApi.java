package org.example.Mapping.Model.Strategy;

import org.example.Mapping.Model.Action.ActionBlockCoreApi;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.EnumAttribute.EnumCustomStrategyTypeUsage;

public interface ExternalStrategyCoreApi extends ActionBlockCoreApi<ExternalStrategyCore> {
	default TwinAttributeStringUsage getContentPath() { return getCore().getContentPath(); }
	default EnumCustomStrategyTypeUsage getStrategyType() { return getCore().getStrategyType(); }
}
