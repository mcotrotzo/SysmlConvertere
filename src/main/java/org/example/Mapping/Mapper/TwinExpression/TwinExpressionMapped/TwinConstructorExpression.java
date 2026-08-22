package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.ConstructorCall;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeDefinitionMapped;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinConstructorExpression<T extends TwinExpression<?>> extends TwinInvocationExpression<T, ConstructorExpression, TwinAttributeDefinitionMapped> implements ConstructorCall<T> {


	public TwinConstructorExpression(ConstructorExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public Reference<? extends TwinAttributeDefinition> getConstructedType() {
		return getInvokeType();
	}
}
