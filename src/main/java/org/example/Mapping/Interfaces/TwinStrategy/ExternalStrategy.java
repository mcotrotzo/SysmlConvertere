package org.example.Mapping.Interfaces.TwinStrategy;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;

public interface ExternalStrategy<T extends TypeKind> extends Strategy<T> {

	Compartment<? extends TwinBaseString<Usage>> getContentPath();

	Compartment<? extends EnumAttribute<CustomStrategyType>> getStrategyType();
}