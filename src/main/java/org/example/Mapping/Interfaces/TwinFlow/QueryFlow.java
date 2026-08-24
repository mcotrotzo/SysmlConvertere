package org.example.Mapping.Interfaces.TwinFlow;


import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;

import java.util.Optional;

public interface QueryFlow extends Flow{
	Optional<TwinBaseIntegerUsage> since();
	Optional<EnumTimeUnit> sinceUnit();
	Optional<EnumOrderBy> orderBy();
	Optional<TwinBaseIntegerUsage> limit();
}
