package org.example.Mapping.Interfaces.TwinFlow;


import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;

import java.util.Optional;

public interface QueryFlow<T extends TypeKind> extends Flow<T>{
	Optional<TwinBaseInteger<Usage>> since();
	Optional<EnumTimeUnit> sinceUnit();
	Optional<EnumOrderBy> orderBy();
	Optional<TwinBaseInteger<Usage>> limit();
}
