package org.example.Mapping.Interfaces;

import java.util.Optional;

public interface Model {
	Optional<Model> getParent();

	String getId();

	String getName();

	KIND getKind();

}
