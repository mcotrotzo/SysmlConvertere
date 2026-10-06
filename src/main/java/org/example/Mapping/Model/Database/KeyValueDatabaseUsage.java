package org.example.Mapping.Model.Database;

import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Feature;

public class KeyValueDatabaseUsage extends DatabaseUsage<DatabaseCore, KeyValueDatabaseDefinition> {
	public KeyValueDatabaseUsage(Feature sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new DatabaseCore(sysmlElement, mapper), mapper, KeyValueDatabaseDefinition.class);
	}
}
