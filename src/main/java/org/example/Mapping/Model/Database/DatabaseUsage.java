package org.example.Mapping.Model.Database;

import java.util.function.Supplier;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;

/** Library type Database. Intermediate class: core and definition class are passed in by the subclass or the registry. */
public class DatabaseUsage<C extends DatabaseCore, D extends DatabaseDefinition> extends Usage<C, Feature, D> implements DatabaseCoreApi<C> {
	public DatabaseUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper mapper, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, mapper, definitionClass);
	}
}
