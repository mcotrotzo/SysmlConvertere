package org.example.Mapping.Mapper.TwinAttributeMapped.LocalAttributeMapped;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.LocalAttribute.Defintion.LocalAttributeDefintion;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeDefinitionMapped;
import org.omg.sysml.lang.sysml.Classifier;

public class LocalAttributeDefinitionMapped extends TwinAttributeDefinitionMapped implements LocalAttributeDefintion {
	public LocalAttributeDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
