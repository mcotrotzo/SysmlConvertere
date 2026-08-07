package org.example.Mapping.Interfaces;

import java.util.List;
import java.util.Optional;

public interface TwinAttribute extends Model {
	Optional<Expression> getTwinExpressions();

	Optional<Reference<? extends TwinAttribute>> getDefinitionReference();
}
