package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.ConstructorUsage;
import Model.Slots;
import org.example.Mapping.Model.Attribute.TwinAttributeDefinition;
import org.omg.sysml.lang.sysml.ConstructorExpression;

/** new Position(x = 1, y = 2, z = 3) */
@MappedMetaClass(value = ConstructorExpression.class, core = EmptyCore.class)
public class TwinConstructorUsage extends ConstructorUsage {

	private static final Class<TwinAttributeDefinition<?>> ATTRIBUTE_DEFINITION = Slots.rawClassOf(TwinAttributeDefinition.class);

	public TwinConstructorUsage(ConstructorExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	// twins only construct twin attribute types; definitions exist once, so mapped without owner
	@Override
	protected void mapConstructorType() {
		constructorType = instance.map(sysmlElement.getInstantiatedType(), null, ATTRIBUTE_DEFINITION);
	}

	@Override
	public TwinAttributeDefinition<?> getConstructorType() {
		return ATTRIBUTE_DEFINITION.cast(constructorType);
	}
}
