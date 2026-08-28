package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;

import java.util.*;

public interface Model<T extends TypeKindNamespace> {
	/**
	 * Returns the parent model of this model, if it exists. The parent contains this model as member
	 *
	 * @return an Optional containing the parent model, or an empty Optional if there is no parent
	 */
	Optional<? extends Model<?>> getParent();

	/**
	 * Returns the unique identifier of this model. The ID is used to distinguish this model from other models in the system.
	 * It changes for different runs of the program, so it should not be used for persistent storage or communication between different instances of the program.
	 *
	 * @return the unique identifier of this model
	 */
	String getId();

	String getName();

	TypeKindNamespace getKind();

	/**
	 * Returns a deterministic ID for this model. This ID is unique and consistent across different runs of the program.
	 * It is generated from the sysml path
	 *
	 * @return a deterministic ID for this model
	 */
	String getDeterministicId();



	String path();

	default Optional<Taxonomy<?>> getTaxonomy() {
		Optional<? extends Model<?>> current = getParent();

		while (current.isPresent()) {
			if (current.get() instanceof Taxonomy<?> taxonomy) {
				return Optional.of(taxonomy);
			}

			current = current.get().getParent();
		}

		return Optional.empty();
	}

	boolean isLibraryElement();
}


