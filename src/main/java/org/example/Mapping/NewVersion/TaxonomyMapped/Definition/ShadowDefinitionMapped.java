package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.ShadowDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.ShadowMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.SHADOW)
@ToString(callSuper = true)
public class ShadowDefinitionMapped extends ShadowMapped<Definition> implements ShadowDefinition {
	public ShadowDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
