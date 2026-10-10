package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

@MappedLibrary(libraryName = "ShadowLibrary::RelationalDatabase", core = DatabaseCore.class)
public class RelationalDatabaseDefinition extends DatabaseDefinition<DatabaseCore> {
	public RelationalDatabaseDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
