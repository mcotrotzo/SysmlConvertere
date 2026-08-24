package org.example.Mapping.NewVersion.TaxonomyMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModelUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PredictiveModelMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;
@MappedElementType(LibraryNameSpaces.PREDICTIVE_MODEL)
@ToString(callSuper = true)
public class PredictiveModelUsageMapped extends PredictiveModelMapped<Feature> implements PredictiveModelUsage {
	public PredictiveModelUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
