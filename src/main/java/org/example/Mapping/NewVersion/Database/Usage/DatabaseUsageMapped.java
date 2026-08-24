package org.example.Mapping.NewVersion.Database.Usage;

import org.example.Mapping.Interfaces.DataBase.Usage.DataBaseUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Database.DatabaseMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.DATABASE)
public abstract class DatabaseUsageMapped extends DatabaseMapped<Feature> implements DataBaseUsage {
	public DatabaseUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
