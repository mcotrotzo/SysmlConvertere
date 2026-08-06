package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.RelationalDatabase;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.RELATIONAL_DATABASE)
@ToString(callSuper = true)
public class RelationalDatabaseMapped extends DatabaseMapped implements RelationalDatabase {

	public RelationalDatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}