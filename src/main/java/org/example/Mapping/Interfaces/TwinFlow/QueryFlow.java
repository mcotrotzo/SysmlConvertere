package org.example.Mapping.Interfaces.TwinFlow;

import EnumOrderBy;
import EnumTimeUnit;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;

import java.util.Optional;

public interface QueryFlow extends Flow{
	Optional<TwinBaseIntegerUsage> since();
	Optional<EnumTimeUnit> sinceUnit();
	Optional<EnumOrderBy> orderBy();
	Optional<TwinBaseIntegerUsage> limit();
}
