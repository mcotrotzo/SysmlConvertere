package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ShadowLibrary::KeyValueDatabase", core = DatabaseCore.class)
public class KeyValueDatabaseDefinition extends DatabaseDefinition<DatabaseCore> {
	public KeyValueDatabaseDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
