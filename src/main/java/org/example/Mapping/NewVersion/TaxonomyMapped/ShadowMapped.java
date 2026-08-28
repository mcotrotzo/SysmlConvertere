package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Shadow;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Database.DatabaseMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.ShadowTaxonomyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.SHADOW)
@ToString(callSuper = true)
public class ShadowMapped<T extends TypeKind>
		extends ShadowTaxonomyMapped<T>
		implements Shadow<T> {

	private CompartmentContainerMapped<DatabaseMapped<Usage>> databases;

	public ShadowMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		databases = context.mapSlot(
				this,
				"databases",
				DatabaseMapped.getRawUsageClass()
		);
	}

	@Override
	public CompartmentContainerMapped<DatabaseMapped<Usage>> getDatabases() {
		return databases;
	}
}