package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_TAXONOMY)
@ToString(callSuper = true)
public class DescriptiveTaxonomyMapped<Z extends TypeKind> extends CloudTwinTaxonomyMapped<Z> implements DescriptiveTaxonomy<Z> {
	public DescriptiveTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
