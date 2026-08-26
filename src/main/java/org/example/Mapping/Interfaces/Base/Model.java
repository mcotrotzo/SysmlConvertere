package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKindNamespace;
import org.example.Mapping.Interfaces.BaseTaxonomy.NoTaxonomyClass;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;

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


	default Class<? extends Taxonomy> getTaxonomy(){
		if(getParent().isEmpty()){
			return NoTaxonomyClass.class;
		}
		return getParent().get().getTaxonomy();
	}
}
