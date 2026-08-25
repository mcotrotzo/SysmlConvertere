package org.example.Mapping.Interfaces.TwinEnumPackage;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;

import java.util.Optional;

public interface EnumAttribute<T extends TwinEnum> extends Type<Usage> {

	Optional<T> getTwinEnum();
}
