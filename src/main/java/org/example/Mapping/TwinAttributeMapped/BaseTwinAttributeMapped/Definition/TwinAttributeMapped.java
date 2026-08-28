package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.Utils.ExpressionRoleValidator;
import org.example.Mapping.TwinExpression.TwinInvocationExpression;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeMapped<T extends TypeKind> extends MappedElement<Type, T> implements TwinAttribute<T> {

	private Role role;
	private org.example.Mapping.TwinExpression.TwinExpression<?> expression;

	public TwinAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	public static Class<TwinAttributeMapped<Definition>> getRawClass() {
		return (Class<TwinAttributeMapped<Definition>>) (Class<?>) TwinAttributeMapped.class;
	}

	public static Class<TwinAttributeMapped<Usage>> getRawUsageClass() {
		return (Class<TwinAttributeMapped<Usage>>) (Class<?>) TwinAttributeMapped.class;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		System.out.println(
				"### ATTRIBUTE PARSE ENTER "
						+ getClass().getName()
						+ " sysml="
						+ getSysmlElement().path()
		);

		super.parse(context);
		if (getSysmlElement() instanceof Feature feature){
			System.out.println(
					"### PARSE USAGE "
							+ getClass().getName()
							+ " sysml="
							+ getSysmlElement().path()
			);

			parseUsage(context, feature);
		}
	}

	private void parseUsage(
			MappingContext context,
			Feature feature
	) throws MappingException {


		var expressions = context.mapOwned(
				this,
				Expression.class,
				org.example.Mapping.TwinExpression.TwinExpression.class
		);


		expression = expressions
				.stream()
				.findFirst()
				.orElse(null);
	}


	private MappedReference<? extends TwinAttributeMapped<Definition>> mapDefinitionReference(MappingContext context, Classifier definition) throws MappingException {
		return context.mapReference(definition, getRawClass());
	}


	@Override
	public Optional<Direction> getDirection() {
		if (!(getSysmlElement() instanceof Feature feature)) return Optional.empty();

		return Optional.of(switch (feature.getDirection()) {
			case IN -> Direction.IN;
			case OUT -> Direction.OUT;
			case INOUT -> Direction.INOUT;
		});
	}


	@Override
	public Optional<TwinExpression> getExpression() {
		return Optional.ofNullable(expression);
	}

	@Override
	public void postValidate() throws MappingException {
		super.postValidate();
		ExpressionRoleValidator.validateAttributeExpression(this,expression);

	}


}