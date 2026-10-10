package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Feature;

@MappedLibrary(libraryName = "ShadowLibrary::KeyValueDatabase", core = DatabaseCore.class)
public class KeyValueDatabaseUsage extends DatabaseUsage<DatabaseCore, KeyValueDatabaseDefinition> {
	public KeyValueDatabaseUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
