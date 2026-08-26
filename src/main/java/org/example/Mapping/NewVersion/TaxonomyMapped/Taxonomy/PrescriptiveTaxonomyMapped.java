package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.PRESCRIPTIVE_TAXONOMY)
@ToString(callSuper = true)
public class PrescriptiveTaxonomyMapped<Z extends TypeKind> extends CloudTwinTaxonomyMapped<Z> implements PrescriptiveTaxonomy<Z> {
	public PrescriptiveTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}