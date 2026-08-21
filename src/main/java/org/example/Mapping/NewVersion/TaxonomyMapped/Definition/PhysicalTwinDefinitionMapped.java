package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwinDefinition;
import org.example.Mapping.NewVersion.TaxonomyMapped.PhysicalTwinMapped;
import org.omg.sysml.lang.sysml.Definition;
@ToString(callSuper = true)
public class PhysicalTwinDefinitionMapped extends PhysicalTwinMapped<Definition> implements PhysicalTwinDefinition {
	public PhysicalTwinDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
