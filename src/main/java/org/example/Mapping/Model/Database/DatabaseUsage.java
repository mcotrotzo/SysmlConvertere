package org.example.Mapping.Model.Database;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type Database. Intermediate class: core and definition class are passed in by the subclass or the registry. */
@MappedLibrary(libraryName = "ShadowLibrary::Database", core = DatabaseCore.class)
public class DatabaseUsage<C extends DatabaseCore, D extends DatabaseDefinition<?>> extends Usage<C, Feature, D> implements DatabaseCoreApi<C> {
	public DatabaseUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
