package org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.CloudTwinTaxonomy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.CLOUD_TWIN_TAXONOMY)
@ToString(callSuper = true)
public class CloudTwinTaxonomyMapped<Z extends TypeKind> extends TaxonomyMapped<Z> implements CloudTwinTaxonomy<Z> {
	public CloudTwinTaxonomyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}