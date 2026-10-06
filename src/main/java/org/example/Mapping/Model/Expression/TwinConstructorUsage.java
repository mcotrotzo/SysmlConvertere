package org.example.Mapping.Model.Expression;

import lombok.Getter;
import org.example.Mapping.Model.Attribute.TwinAttributeDefinition;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.ConstructorExpression;

/** new Position(x = 1, y = 2, z = 3) */
public class TwinConstructorUsage extends TwinInvocationUsage<ConstructorExpression> {

	@Getter private TwinAttributeDefinition<?> constructorType;

	public TwinConstructorUsage(ConstructorExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		// definitions exist once: mapped without owner
		constructorType = instance.map(sysmlElement.getInstantiatedType(), null, TwinAttributeDefinition.class);
	}
}
