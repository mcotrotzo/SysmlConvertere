package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.BaseTaxonomy.Context.ShadowContext;
import org.example.Mapping.Interfaces.Database;

import java.util.List;

public interface Shadow extends ShadowContext ,Taxonomy{

	List<Database> getDatabases();

}
