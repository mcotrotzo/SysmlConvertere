package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModelDefinition;
import org.example.Mapping.NewVersion.TaxonomyMapped.DescriptiveModelMapped;
import org.omg.sysml.lang.sysml.Classifier;
@ToString(callSuper = true)
public class DescriptiveModelDefinitionMapped extends DescriptiveModelMapped<Classifier> implements DescriptiveModelDefinition {
	public DescriptiveModelDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
