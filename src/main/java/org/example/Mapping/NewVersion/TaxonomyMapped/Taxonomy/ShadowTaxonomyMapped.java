package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.ShadowTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.SHADOW_TAXONOMY)
@ToString(callSuper = true)
public class ShadowTaxonomyMapped<Z extends TypeKind> extends TaxonomyMapped<Z> implements ShadowTaxonomy<Z> {
	public ShadowTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}