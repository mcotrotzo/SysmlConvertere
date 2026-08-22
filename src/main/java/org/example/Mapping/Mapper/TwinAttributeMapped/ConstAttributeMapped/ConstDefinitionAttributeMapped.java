package org.example.Mapping.Mapper.TwinAttributeMapped.ConstAttributeMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConstAttribute.Definition.ConstDefinitionAttribute;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeDefinitionMapped;
import org.omg.sysml.lang.sysml.Classifier;

public class ConstDefinitionAttributeMapped
        extends TwinAttributeDefinitionMapped
        implements ConstDefinitionAttribute {

    public ConstDefinitionAttributeMapped(Classifier sysmlElement) {
        super(sysmlElement);
    }
}
