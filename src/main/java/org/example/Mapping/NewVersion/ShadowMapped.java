package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Database;
import org.example.Mapping.Interfaces.Shadow;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.SHADOW)
@ToString(callSuper = true)
public class ShadowMapped extends MappedElement<Type> implements Shadow {
	List<DatabaseMapped> databases = new ArrayList<>();

	public ShadowMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		databases = context.mapSlot(this, "databases", DatabaseMapped.class);
	}



	@Override
	public List<Database> getDatabases() {
		return new ArrayList<>(databases);
	}
}
