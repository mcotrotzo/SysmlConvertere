package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;
import java.util.Optional;

public interface Type<T extends TypeKind> extends NameSpace<T> {


	Optional<? extends Reference<? extends Type<Definition>>> getDefinitionOfUsage();

	List<? extends Reference<? extends Type<Definition>>> getSuperTypeOfDefinitions();


	default boolean isSubtypeOf(Type<Definition> expected) {
		if (this == expected) {
			return true;
		}

		return getSuperTypeOfDefinitions().stream()
				.map(Reference::getReferent)
				.anyMatch(superType -> superType.isSubtypeOf(expected));
	}

}
