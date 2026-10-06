package org.example.Mapping.Model.Attribute;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Slots;
import org.example.Mapping.Model.Taxonomy.Base.TaxonomyUsage;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class TwinAttributeUsage<C extends TwinAttributeCore, D extends TwinAttributeDefinition> extends Usage<C, Feature, D> implements TwinAttributeCoreApi<C> {

	@Getter private Optional<TwinExpressionUsage> expression = Optional.empty();
	@Getter private final Set<Role> roles = EnumSet.noneOf(Role.class);

	public TwinAttributeUsage(Feature sysmlElement, Supplier<C> coreFactory, Mapper newMappe, Class<D> definitionClass) {
		super(sysmlElement, coreFactory, newMappe, definitionClass);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		expression = instance.mapOwnedElement(Expression.class, this, TwinExpressionUsage.class).stream().findFirst();
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public Optional<TaxonomyUsage<?, ?>> getTaxonomy() {
		Class<TaxonomyUsage<?, ?>> taxonomyClass = Slots.rawClassOf(TaxonomyUsage.class);
		for (AbstractModel<?> current = getParent().orElse(null); current != null; current = current.getParent().orElse(null)) {
			if (taxonomyClass.isInstance(current)) {
				return Optional.of(taxonomyClass.cast(current));
			}
		}
		return Optional.empty();
	}
}
