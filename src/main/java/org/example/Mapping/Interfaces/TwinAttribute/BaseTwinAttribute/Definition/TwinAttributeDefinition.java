package org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition;

import org.example.Mapping.Interfaces.Base.Definition;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.Reference;

import java.util.List;

public interface TwinAttributeDefinition extends Definition {
    List<Reference<? extends TwinAttributeDefinition>> getSuperTypes();
}
