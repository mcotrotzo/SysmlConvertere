package org.example.Mapping.Interfaces.TwinExpression;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;

import java.util.List;


public interface FeatureReference extends TwinExpression {

	public List<MappedReference<? extends Type<Usage>>>  getChain();
}
