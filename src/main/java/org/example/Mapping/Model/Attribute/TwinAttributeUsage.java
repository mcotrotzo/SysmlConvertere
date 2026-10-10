package org.example.Mapping.Model.Attribute;



import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import Model.Usage;
import lombok.Getter;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyUsage;
import Model.Predefined.MetaClasses.Expression.ExpressionUsage;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

@MappedLibrary(libraryName = "Base::DataValue", core = TwinAttributeCore.class)
public class TwinAttributeUsage<C extends TwinAttributeCore, D extends TwinAttributeDefinition<?>> extends Usage<C, Feature, D> implements TwinAttributeCoreApi<C> {

	@Getter private Optional<ExpressionUsage<?>> expression = Optional.empty();
	@Getter private final Set<Role> roles = EnumSet.noneOf(Role.class);

	public TwinAttributeUsage(Feature sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		expression = instance.mapOwnedElement(Expression.class, this, Slots.<ExpressionUsage<?>>rawClassOf(ExpressionUsage.class)).stream().findFirst();
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public Optional<TaxonomyUsage<?, ?>> getTaxonomy() {
		Class<TaxonomyUsage<?, ?>> taxonomyClass = Slots.rawClassOf(TaxonomyUsage.class);
		for (AbstractType<?, ?> current = getParent().orElse(null); current != null; current = current.getParent().orElse(null)) {
			if (taxonomyClass.isInstance(current)) {
				return Optional.of(taxonomyClass.cast(current));
			}
		}
		return Optional.empty();
	}
}
