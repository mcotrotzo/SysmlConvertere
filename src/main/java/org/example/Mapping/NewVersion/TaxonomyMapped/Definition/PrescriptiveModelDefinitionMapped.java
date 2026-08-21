package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModelDefinition;
import org.example.Mapping.NewVersion.TaxonomyMapped.PrescriptiveModelMapped;
import org.omg.sysml.lang.sysml.Definition;
@ToString(callSuper = true)
public class PrescriptiveModelDefinitionMapped extends PrescriptiveModelMapped<Definition> implements PrescriptiveModelDefinition {
	public PrescriptiveModelDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
