package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.eclipse.uml2.uml.internal.impl.DataTypeImpl;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.lang.sysml.util.SysMLSwitch;
import org.omg.sysml.util.TypeUtil;

import java.lang.Class;
import java.util.*;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeMapped extends MappedElement<Feature> implements TwinAttribute {
	private List<TwinExpression<?>> twinExpressions = new ArrayList<>();
	protected MappedReference<BaseTypeDefinitionMapped> typeReference;
	public TwinAttributeMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		Class<TwinExpression<?>> rawClass = (Class<TwinExpression<?>>) (Class<?>) TwinExpression.class;
		for (Expression expression :
				getSysmlElement().getOwnedElement()
						.stream()
						.filter(Expression.class::isInstance)
						.map(Expression.class::cast)
						.toList()) {

			System.out.println(
					"ATTRIBUTE: " + getName()
							+ " SYSML EXPRESSION: "
							+ expression.getClass().getName()
			);
		}

		twinExpressions =context.mapOwned(this, Expression.class, rawClass);
		resolveTypeReference(context);
	}

	@Override
	public Optional<org.example.Mapping.Interfaces.Expression> getTwinExpressions() {
		if (twinExpressions == null || twinExpressions.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(twinExpressions.iterator().next());
	}

	@Override
	public Reference<? extends TwinTypeDefinition> getDefinition() {
		return typeReference;
	}

	protected void resolveTypeReference(MappingContext context)
			throws MappingException {

		Set<Classifier> candidates = new LinkedHashSet<>();

		collectInheritedClassifiers(
				getSysmlElement(),
				new HashSet<>(),
				candidates
		);

		Classifier target = candidates.stream()
				.filter(candidate ->
						candidates.stream().noneMatch(other ->
								other != candidate
										&& TypeUtil.specializes(other, candidate)
						)
				)
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"%s has no type definition"
										.formatted(getName())
						)
				);

		typeReference = context.mapReference(
				target,
				BaseTypeDefinitionMapped.class
		);
	}

	private void collectInheritedClassifiers(
			Type type,
			Set<Type> visited,
			Set<Classifier> result
	) {

		if (!visited.add(type)) {
			return;
		}

		for (Type superType : TypeUtil.getSupertypesOf(type)) {

			if (superType instanceof Classifier classifier) {
				result.add(classifier);
			}

			collectInheritedClassifiers(
					superType,
					visited,
					result
			);
		}
	}

}
