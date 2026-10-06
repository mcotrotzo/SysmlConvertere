package org.example.Mapping.Model.Database;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class RelationalDatabaseUsage extends DatabaseUsage<DatabaseCore, RelationalDatabaseDefinition> {
	public RelationalDatabaseUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DatabaseCore(sysmlElement, mapper), mapper, RelationalDatabaseDefinition.class);
	}
}
