package org.example.Mapping.NewVersion.Database.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.DataBase.Usage.KeyValueDatabaseUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.KEY_VALUE_DATABASE)
@ToString(callSuper = true)
public class KeyValueDataBaseUsageMapped extends DatabaseUsageMapped implements KeyValueDatabaseUsage {
	public KeyValueDataBaseUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
