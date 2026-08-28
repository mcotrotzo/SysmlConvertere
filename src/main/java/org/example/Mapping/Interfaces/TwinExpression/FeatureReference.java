package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;


public interface FeatureReference extends TwinExpression {

	public List<? extends Reference<? extends Type<Usage>>>  getChain();
	public Reference<? extends Compartment<? extends Type<Usage>>> getAsCompartment();
}
