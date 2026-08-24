package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModelDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PrescriptiveModelMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelDefinitionMapped extends PrescriptiveModelMapped<Definition> implements PrescriptiveModelDefinition {
	public PrescriptiveModelDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
