package org.example.Mapping.Interfaces;

import org.example.ElemWithMult;

import java.util.Optional;

public interface Model extends ReadWritePermissions {
	/**
	 * Returns the parent model of this model, if it exists. The parent contains this model as member
	 *
	 * @return an Optional containing the parent model, or an empty Optional if there is no parent
	 */
	Optional<Model> getParent();

	String getId();

	String getName();

	KIND getKind();

}
