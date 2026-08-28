package org.example.Mapping.Interfaces.TwinAction;


import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.Optional;

public interface WhileLoop extends Action<Usage> {
	TwinExpression getCondition();

	TwinExpression getUntil();

	Optional<? extends Compartment<? extends Action<Usage>>> getBody();
}
