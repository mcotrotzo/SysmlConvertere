package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.ShadowDefinition;
import org.example.Mapping.NewVersion.TaxonomyMapped.ShadowMapped;
import org.omg.sysml.lang.sysml.Definition;
@ToString(callSuper = true)
public class ShadowDefinitionMapped extends ShadowMapped<Definition> implements ShadowDefinition {
	public ShadowDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
