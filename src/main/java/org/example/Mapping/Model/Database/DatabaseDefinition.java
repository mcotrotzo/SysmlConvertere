package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type Database. Intermediate class: the concrete core is passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "ShadowLibrary::Database", core = DatabaseCore.class)
public class DatabaseDefinition<C extends DatabaseCore> extends Definition<C, Classifier> implements DatabaseCoreApi<C> {
	public DatabaseDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
