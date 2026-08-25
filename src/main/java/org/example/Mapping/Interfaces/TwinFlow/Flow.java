package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

import java.util.Set;

public interface Flow<T extends TypeKind> extends Type<Usage> {
	Set<Context> sourceContexts();

	Set<Context> targetContexts();

	Reference<? extends TwinAttribute<Usage>> getSource();
	Reference<? extends  TwinAttribute<Usage>> getTarget();
}
