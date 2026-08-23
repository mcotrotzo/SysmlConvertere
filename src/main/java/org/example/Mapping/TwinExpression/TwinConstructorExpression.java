package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.TwinExpression.ConstructorCall;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeDefinitionMapped;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinConstructorExpression extends TwinInvocationExpression<ConstructorExpression, TwinAttributeDefinitionMapped> implements ConstructorCall {


	public TwinConstructorExpression(ConstructorExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public Reference<? extends TwinAttributeDefinition> getConstructedType() {
		return getInvokeType();
	}
}
