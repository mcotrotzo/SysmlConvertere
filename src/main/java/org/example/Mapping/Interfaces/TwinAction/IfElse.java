package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.Optional;

public interface IfElse extends Action<Usage> {

	TwinExpression getCondition();

	Compartment<? extends Action<Usage>> getThenAction();

	Optional<? extends Compartment<? extends Action<Usage>>> getElseAction();
}