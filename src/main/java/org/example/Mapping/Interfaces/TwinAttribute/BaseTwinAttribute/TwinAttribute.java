package org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.List;
import java.util.Optional;

public interface TwinAttribute<T extends TypeKind> extends Type<T> {
	@Override
	Optional<Reference<? extends TwinAttribute<Definition>>> getDefinitionOfUsage();

	@Override
	List<Reference<? extends TwinAttribute<Definition>>> getSuperTypeOfDefinitions();

	Optional<Direction> getDirection();

	Role getRole();

	Optional<TwinExpression> getExpression();
}
