package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumFederationLink;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;

import java.util.Optional;

public interface FederationFlow <T extends TypeKind> extends Flow<T>{

	Optional<? extends Compartment<? extends EnumAttribute<EnumFederationLink>>> linkType();
}
