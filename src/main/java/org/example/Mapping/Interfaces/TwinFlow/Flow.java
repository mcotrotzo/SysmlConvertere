package org.example.Mapping.Interfaces.TwinFlow;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;

public interface Flow<T extends TypeKind> extends Type<T> {
	Taxonomy<Usage> sourceContexts();

	Taxonomy<Usage> targetContexts();

	Reference<? extends TwinAttribute<Usage>> getSource();

	Reference<? extends TwinAttribute<Usage>> getTarget();
}
