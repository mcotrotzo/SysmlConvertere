package org.example.Mapping.NewVersion.Database.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.DataBase.Usage.RelationalDatabaseUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.RELATIONAL_DATABASE)
@ToString(callSuper = true)
public class RelationalDatabaseUsageMapped extends DatabaseUsageMapped implements RelationalDatabaseUsage {
	public RelationalDatabaseUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
