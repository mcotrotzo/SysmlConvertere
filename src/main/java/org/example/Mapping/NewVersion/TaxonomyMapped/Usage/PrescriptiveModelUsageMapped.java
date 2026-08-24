package org.example.Mapping.NewVersion.TaxonomyMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModelUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PrescriptiveModelMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;
@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_MODEL)
@ToString(callSuper = true)
public class PrescriptiveModelUsageMapped extends PrescriptiveModelMapped<Feature> implements PrescriptiveModelUsage {
	public PrescriptiveModelUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
