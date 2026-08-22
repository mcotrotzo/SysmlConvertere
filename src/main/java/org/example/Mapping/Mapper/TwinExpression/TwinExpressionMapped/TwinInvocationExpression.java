package org.example.Mapping.Mapper.TwinExpression.TwinExpressionMapped;


import lombok.Getter;
import lombok.ToString;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.ConstructorExpression;
import org.omg.sysml.lang.sysml.InstantiationExpression;

import java.util.ArrayList;
import java.util.List;

@MappedMetaclass
@ToString(callSuper = true)
public abstract class TwinInvocationExpression<T extends TwinExpression<?>, U extends InstantiationExpression,Z extends MappedElementDefinition<?>> extends TwinExpression<U> {

	private final List<T> arguments = new ArrayList<>();
	@Getter
	private MappedReference<Z> invokeType;

	public TwinInvocationExpression(U sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		Class<T> argumentType = resolveArgumentType();

		for (var arg : getSysmlElement().getArgument()) {
			arguments.add(context.map(arg, this, argumentType));
		}

		invokeType = context.mapReference(this.getSysmlElement().getInstantiatedType(),resolveInvokeType());
	}

	@SuppressWarnings("unchecked")
	private Class<T> resolveArgumentType() {

		java.lang.reflect.Type genericSuperclass = getClass().getGenericSuperclass();

		if (!(genericSuperclass instanceof java.lang.reflect.ParameterizedType type)) {
			throw new IllegalStateException("Expected parameterized invocation expression superclass.");
		}

		java.lang.reflect.Type argument = type.getActualTypeArguments()[0];

		if (!(argument instanceof Class<?> clazz)) {
			throw new IllegalStateException("Expected concrete expression class, but got: " + argument);
		}

		return (Class<T>) clazz;
	}

	@SuppressWarnings("unchecked")
	private Class<Z> resolveInvokeType() {

		java.lang.reflect.Type genericSuperclass =
				getClass().getGenericSuperclass();

		if (!(genericSuperclass instanceof java.lang.reflect.ParameterizedType type)) {
			throw new IllegalStateException(
					"Expected parameterized invocation expression superclass."
			);
		}

		java.lang.reflect.Type argument =
				type.getActualTypeArguments()[2];

		if (!(argument instanceof Class<?> clazz)) {
			throw new IllegalStateException(
					"Expected concrete invocation type class, but got: " + argument
			);
		}

		return (Class<Z>) clazz;
	}

	public List<T> getArguments() {
		return List.copyOf(arguments);
	}
}
