package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.BaseTaxonomy.Context.Context;
import org.example.Mapping.Interfaces.Database;

import java.util.List;

public interface Shadow extends Taxonomy {

	List<Database> getDatabases();

	@Override
	default Context getContext() {
		return Context.SHADOW;
	}
}
