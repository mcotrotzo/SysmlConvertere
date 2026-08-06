package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.KeyValueDatabase;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.KEY_VALUE_DATABASE)
@ToString(callSuper = true)
public class KeyValueDatabaseMapped extends DatabaseMapped implements KeyValueDatabase {

	public KeyValueDatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}