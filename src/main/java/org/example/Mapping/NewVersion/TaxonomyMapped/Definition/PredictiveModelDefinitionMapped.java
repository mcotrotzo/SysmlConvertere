package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModelDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PredictiveModelMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelDefinitionMapped extends PredictiveModelMapped<Definition> implements PredictiveModelDefinition {
	public PredictiveModelDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
