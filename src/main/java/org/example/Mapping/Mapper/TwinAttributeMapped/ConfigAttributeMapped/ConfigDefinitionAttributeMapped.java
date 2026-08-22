package org.example.Mapping.Mapper.TwinAttributeMapped.ConfigAttributeMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.ConfigAttribute.Defintion.ConfigAttributeDefinition;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeDefinitionMapped;
import org.omg.sysml.lang.sysml.Classifier;

public class ConfigDefinitionAttributeMapped extends TwinAttributeDefinitionMapped implements ConfigAttributeDefinition {
	public ConfigDefinitionAttributeMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
