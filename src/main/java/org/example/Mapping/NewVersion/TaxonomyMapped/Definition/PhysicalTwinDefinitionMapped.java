package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwinDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PhysicalTwinMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinDefinitionMapped extends PhysicalTwinMapped<Definition> implements PhysicalTwinDefinition {
	public PhysicalTwinDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
