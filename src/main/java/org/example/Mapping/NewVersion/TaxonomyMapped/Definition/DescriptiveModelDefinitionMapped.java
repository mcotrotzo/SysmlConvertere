package org.example.Mapping.NewVersion.TaxonomyMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModelDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.DescriptiveModelMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class DescriptiveModelDefinitionMapped extends DescriptiveModelMapped<Classifier> implements DescriptiveModelDefinition {
	public DescriptiveModelDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
