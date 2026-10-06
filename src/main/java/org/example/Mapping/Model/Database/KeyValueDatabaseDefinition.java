package org.example.Mapping.Model.Database;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class KeyValueDatabaseDefinition extends DatabaseDefinition<DatabaseCore> {
	public KeyValueDatabaseDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DatabaseCore(sysmlElement, mapper), mapper);
	}
}
