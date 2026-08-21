package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModelDefinition;
import org.example.Mapping.NewVersion.TaxonomyMapped.PredictiveModelMapped;
import org.omg.sysml.lang.sysml.Definition;
@ToString(callSuper = true)
public class PredictiveModelDefinitionMapped extends PredictiveModelMapped<Definition> implements PredictiveModelDefinition {
	public PredictiveModelDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
