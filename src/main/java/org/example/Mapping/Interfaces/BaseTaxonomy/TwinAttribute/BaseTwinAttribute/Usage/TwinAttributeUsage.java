package org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage;

import org.example.Mapping.Interfaces.Base.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;

public interface TwinAttributeUsage extends TwinAttribute, Usage {
    Reference<? extends TwinTypeDefinition> getDefinition();
}
