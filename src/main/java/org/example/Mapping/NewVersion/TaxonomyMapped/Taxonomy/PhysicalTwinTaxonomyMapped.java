package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PHYSICAL_TAXONOMY)
@ToString(callSuper = true)
public class PhysicalTwinTaxonomyMapped<Z extends TypeKind> extends TaxonomyMapped<Z> implements PhysicalTaxonomy<Z> {
	public PhysicalTwinTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}

}
