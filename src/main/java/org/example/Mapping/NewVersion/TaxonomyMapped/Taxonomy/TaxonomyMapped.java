package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TWIN_TAXONOMY)
@ToString(callSuper = true)
public class TaxonomyMapped<Z extends TypeKind> extends MappedElement<Type, Z> implements org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy<Z> {
	public TaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
