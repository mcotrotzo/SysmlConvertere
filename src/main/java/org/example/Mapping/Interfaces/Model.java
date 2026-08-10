package org.example.Mapping.Interfaces;

import org.example.ElemWithMult;

import java.util.Optional;

public interface Model extends ReadWritePermissions {
	Optional<Model> getParent();

	String getId();

	String getName();

	KIND getKind();

}
