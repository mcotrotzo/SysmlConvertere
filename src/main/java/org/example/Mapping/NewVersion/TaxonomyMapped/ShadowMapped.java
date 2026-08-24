package org.example.Mapping.NewVersion.TaxonomyMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.BaseTaxonomy.Shadow;
import org.example.Mapping.Interfaces.DataBase.Usage.DataBaseUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Database.DatabaseMapped;
import org.example.Mapping.NewVersion.Database.Usage.DatabaseUsageMapped;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.SHADOW)
@ToString(callSuper = true)
public class ShadowMapped<T extends Type> extends MappedElement<T> implements Shadow {
	List<DatabaseUsageMapped> databases = new ArrayList<>();

	public ShadowMapped(T sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		databases = context.mapSlot(this, "databases", DatabaseUsageMapped.class);
	}



	@Override
	public List<DataBaseUsage> getDatabases() {
		return new ArrayList<>(databases);
	}
}
