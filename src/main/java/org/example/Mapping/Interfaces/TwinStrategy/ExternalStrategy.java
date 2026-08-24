package org.example.Mapping.Interfaces.TwinStrategy;

import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;

import java.util.List;

public interface ExternalStrategy extends Strategy{

	TwinBaseStringUsage getContentPath();
	CustomStrategyType getStrategyType();

}
