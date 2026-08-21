package org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Definition;

import org.example.Mapping.Interfaces.Base.Definition;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;

public interface TwinAttributeDefinition extends TwinAttribute, Definition {
    List<Reference<? extends TwinAttributeDefinition>> getSuperTypes();
}
