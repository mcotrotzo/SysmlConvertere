package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "ShadowLibrary::RelationalDatabase", core = DatabaseCore.class)
public class RelationalDatabaseUsage extends DatabaseUsage<DatabaseCore, RelationalDatabaseDefinition> {
	public RelationalDatabaseUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
