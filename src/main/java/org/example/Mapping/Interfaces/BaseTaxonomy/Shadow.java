package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.DataBase.Database;

public interface Shadow<T extends TypeKind> extends CloudTwinTaxonomy<T> {

	CompartmentContainer<? extends Database<Usage>> getDatabases();
}