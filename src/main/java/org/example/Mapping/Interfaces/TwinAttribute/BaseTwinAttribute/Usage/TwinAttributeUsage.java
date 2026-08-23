package org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage;

import org.example.Mapping.Interfaces.Base.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;

import java.util.Optional;

public interface TwinAttributeUsage extends Usage {
    Reference<? extends TwinAttributeDefinition> getDefinition();
	Direction getDirection();
	Role getRole();

	Optional<TwinExpression> getExpression();
}
