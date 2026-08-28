package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.BaseTaxonomy.NoTaxonomyClass;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

	default boolean resolveRole() {
		boolean changed = false;

		System.out.println(
				"RESOLVE"
						+ " path=" + path()
						+ " name=" + getName()
						+ " parent_name="
						+ getParent().map(Model::getName).orElse("null")
						+ " parent_path="
						+ getParent().map(Model::path).orElse("null")
						+ " before=" + getRoleClasses()
		);

		if (getParent().isPresent()) {
			for (RoleClass role : getParent().get().getRoleClasses()) {

				boolean added = addRole(role);

				if (added) {
					System.out.println(
							"  PARENT ROLE -> "
									+ "path=" + path()
									+ " name=" + getName()
									+ " gets=" + role
									+ " from_path=" + getParent().get().path()
									+ " from_name=" + getParent().get().getName()
					);
				}

				changed |= added;
			}
		}

		return changed;
	}

	Collection<RoleClass> getRoleClasses();

	boolean addRole(RoleClass role);

	String path();
}


