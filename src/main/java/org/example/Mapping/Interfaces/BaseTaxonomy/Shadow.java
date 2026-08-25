package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.DataBase.Database;

import java.util.List;

public interface Shadow<T extends TypeKind> extends CloudTwinTaxonomy<T> {

	List<Database<Usage>> getDatabases();

}
