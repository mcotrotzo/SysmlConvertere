package org.example.Mapping.Mapper.TwinExpression;

import lombok.ToString;
import org.example.Mapping.Interfaces.CollectionExpression;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinTypeDefinitionMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.CollectExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public class TwinCollectionExpression extends TwinExpression<CollectExpression> implements CollectionExpression {

	private List<TwinExpression<?>> arguments = new ArrayList<>();
	private MappedReference<TwinTypeDefinitionMapped> collectionType;

	public TwinCollectionExpression(CollectExpression sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		collectionType = context.mapReference(getSysmlElement().getInstantiatedType(), TwinTypeDefinitionMapped.class);

		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, TwinExpression.class));
		}

	}


	@Override
	public Reference<? extends TwinTypeDefinition> getCollectionType() {

		return collectionType;
	}


	@Override
	public List<Expression> getArguments() {
		return new ArrayList<>(arguments);
	}
}
