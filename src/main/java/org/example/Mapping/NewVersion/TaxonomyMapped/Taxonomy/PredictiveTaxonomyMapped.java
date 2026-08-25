package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PREDICTIVE_TAXONOMY)
@ToString(callSuper = true)
public class PredictiveTaxonomyMapped<Z extends TypeKind> extends TaxonomyMapped<Z> implements PredictiveTaxonomy<Z> {
	public PredictiveTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}