package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;

import java.util.List;

public interface DTLibrary extends Package{

	List<? extends Type<Definition>> getLibraryDefinitions();
}
