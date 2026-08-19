package org.example.Mapping.Mapper.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.ConstructorCall;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.example.Mapping.NewVersion.TwinTypeDefinitionMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinConstructorExpression extends TwinExpression<ConstructorExpression> implements ConstructorCall {

	private List<TwinExpression<?>> arguments = new ArrayList<>();
	private MappedReference<TwinTypeDefinitionMapped> constructedType;

	public TwinConstructorExpression(ConstructorExpression sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public List<Expression> getArguments() {
		return new ArrayList<>(arguments);
	}

	@Override
	public Reference<? extends TwinTypeDefinition> getConstructedType() {
		return constructedType;
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		constructedType = context.mapReference(getSysmlElement().getInstantiatedType(), TwinTypeDefinitionMapped.class);

		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, TwinExpression.class));
		}
	}
}
