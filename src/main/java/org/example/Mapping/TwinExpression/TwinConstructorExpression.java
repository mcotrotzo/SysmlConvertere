package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.ConstructorCall;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinConstructorExpression extends TwinInvocationExpression<ConstructorExpression, TwinAttributeMapped> implements ConstructorCall {


	public TwinConstructorExpression(ConstructorExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<TwinAttributeMapped> resolveInvokeType() {
		return TwinAttributeMapped.class;
	}

	@Override
	public Reference<? extends TwinAttribute> getConstructedType() {
		return getInvokeType();
	}
}
