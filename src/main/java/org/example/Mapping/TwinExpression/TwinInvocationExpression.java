package org.example.Mapping.TwinExpression;


import lombok.Getter;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.InstantiationExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
public abstract class TwinInvocationExpression<U extends InstantiationExpression, Z extends MappedElement<?, ?>> extends TwinExpression<U> {

	private final List<TwinExpression<?>> arguments = new ArrayList<>();

	@Getter
	private MappedReference<Z> invokeType;

	public TwinInvocationExpression(U sysmlElement) {
		super(sysmlElement);
	}

	protected abstract Class<Z> resolveInvokeType();

	@Override
	public void parse(MappingContext context) throws MappingException {

		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, TwinExpression.class));
		}

		invokeType = context.mapReference(getSysmlElement().getInstantiatedType(), resolveInvokeType());
	}

	public List<? extends org.example.Mapping.Interfaces.TwinExpression.TwinExpression> getArguments() {
		return List.copyOf(arguments);
	}
}
