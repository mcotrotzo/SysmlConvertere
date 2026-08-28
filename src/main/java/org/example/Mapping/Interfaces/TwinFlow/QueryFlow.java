package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;

import java.util.Optional;

public interface QueryFlow<T extends TypeKind> extends Flow<T> {

	Optional<? extends Compartment<? extends TwinBaseInteger<Usage>>> since();

	Optional<? extends Compartment<? extends EnumAttribute<EnumTimeUnit>>> sinceUnit();

	Optional<? extends Compartment<? extends EnumAttribute<EnumOrderBy>>> orderBy();

	Optional<? extends Compartment<? extends TwinBaseInteger<Usage>>> limit();
}