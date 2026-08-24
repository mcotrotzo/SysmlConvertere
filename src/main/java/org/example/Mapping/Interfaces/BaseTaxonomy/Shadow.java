package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.DataBase.Usage.DataBaseUsage;

import java.util.List;

public interface Shadow extends Taxonomy {

	List<DataBaseUsage> getDatabases();

	@Override
	default Context getContext() {
		return Context.SHADOW;
	}
}
