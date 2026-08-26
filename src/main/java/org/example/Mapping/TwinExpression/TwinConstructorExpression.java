package org.example.Mapping.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.ConstructorCall;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.omg.sysml.lang.sysml.ConstructorExpression;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinConstructorExpression extends TwinInvocationExpression<ConstructorExpression> implements ConstructorCall {

	MappedReference<? extends TwinAttributeMapped<Definition>> constructedType;

	public TwinConstructorExpression(ConstructorExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		constructedType = context.mapReference(getSysmlElement().getInstantiatedType(),rawClassOf(TwinAttributeMapped.class));
	}

	@Override
	public Reference<? extends TwinAttribute<Definition>> getConstructedType() {
		return constructedType;
	}
}
