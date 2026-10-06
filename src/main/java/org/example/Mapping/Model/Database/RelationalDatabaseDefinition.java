package org.example.Mapping.Model.Database;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Classifier;

public class RelationalDatabaseDefinition extends DatabaseDefinition<DatabaseCore> {
	public RelationalDatabaseDefinition(Classifier sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DatabaseCore(sysmlElement, mapper), mapper);
	}
}
