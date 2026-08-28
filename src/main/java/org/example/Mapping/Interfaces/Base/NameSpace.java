package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;

import java.util.Optional;

public interface NameSpace<T extends TypeKindNamespace> extends Model<T> {

}
