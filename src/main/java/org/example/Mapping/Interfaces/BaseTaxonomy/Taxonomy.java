package org.example.Mapping.Interfaces.BaseTaxonomy;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;

import java.util.Optional;

public interface Taxonomy<T extends TypeKind> extends Type<T> {

	@Override
	default Optional<Taxonomy<?>> getTaxonomy() {
		return Optional.of(this);
	}
}
