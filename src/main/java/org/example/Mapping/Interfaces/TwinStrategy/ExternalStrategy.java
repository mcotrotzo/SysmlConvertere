package org.example.Mapping.Interfaces.TwinStrategy;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;

import java.util.List;

public interface ExternalStrategy<T extends TypeKind> extends Strategy<T>{

	TwinBaseString<Usage> getContentPath();
	CustomStrategyType getStrategyType();

}
