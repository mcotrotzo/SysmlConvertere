package org.example.Mapping.Model.Database;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Definition;
import org.omg.sysml.lang.sysml.Classifier;

/** Library type Database. Intermediate class: the concrete core is passed in by the subclass or the registry. */
public class DatabaseDefinition<C extends DatabaseCore> extends Definition<C, Classifier> implements DatabaseCoreApi<C> {
	public DatabaseDefinition(Classifier sysmlElement, Supplier<C> coreFactory, Mapper mapper) {
		super(sysmlElement, coreFactory, mapper);
	}
}
