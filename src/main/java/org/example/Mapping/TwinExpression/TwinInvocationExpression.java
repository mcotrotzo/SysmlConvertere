package org.example.Mapping.TwinExpression;


import lombok.Getter;
import lombok.ToString;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.InstantiationExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public abstract class TwinInvocationExpression<U extends InstantiationExpression,Z extends MappedElementDefinition<?>> extends TwinExpression<U> {

	private final List<TwinExpression<?>> arguments = new ArrayList<>();
	@Getter
	private MappedReference<Z> invokeType;

	public TwinInvocationExpression(U sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, TwinExpression.class));
		}

		invokeType = context.mapReference(this.getSysmlElement().getInstantiatedType(),resolveInvokeType());
	}

	public List<? extends org.example.Mapping.Interfaces.TwinExpression.TwinExpression> getArguments() {
		return List.copyOf(arguments);
	}
}
