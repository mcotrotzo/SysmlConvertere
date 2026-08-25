package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeMapped<T extends TypeKind> extends MappedElement<Type, T> implements TwinAttribute<T> {

	private final List<MappedReference<? extends TwinAttributeMapped<Definition>>> superTypes = new ArrayList<>();
	private MappedReference<? extends TwinAttributeMapped<Definition>> typeReference;
	private Role role;
	private org.example.Mapping.TwinExpression.TwinExpression<?> expression;

	public TwinAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}



	@Override
	public void parse(MappingContext context) throws MappingException {
		if (getSysmlElement() instanceof Feature feature) parseUsage(context, feature);
		if (getSysmlElement() instanceof Classifier classifier) parseDefinition(context, classifier);
	}

	private void parseUsage(MappingContext context, Feature feature) throws MappingException {
		expression = context.mapOwned(this, Expression.class, org.example.Mapping.TwinExpression.TwinExpression.class).stream().findFirst().orElse(null);

		Classifier definition = feature.getType().stream().filter(Classifier.class::isInstance).map(Classifier.class::cast).findFirst().orElseThrow(() -> new MappingException(("No twin attribute definition found for '%s'.").formatted(getName())));

		typeReference = mapDefinitionReference(context, definition);
	}

	private void parseDefinition(MappingContext context, Classifier classifier) throws MappingException {
		for (var subclassification : classifier.getOwnedSubclassification()) {
			var superClassifier = subclassification.getSuperclassifier();

			if (!(superClassifier instanceof Classifier definition)) continue;
			if (context.getUtils().isFromStandardOrDTLibrary(definition)) continue;

			superTypes.add(mapDefinitionReference(context, definition));
		}
	}


	public static Class<TwinAttributeMapped<Definition>> getRawClass() {
		return (Class<TwinAttributeMapped<Definition>>) (Class<?>) TwinAttributeMapped.class;
	}

	public static Class<TwinAttributeMapped<Usage>> getRawUsageClass() {
		return (Class<TwinAttributeMapped<Usage>>) (Class<?>) TwinAttributeMapped.class;
	}
	private MappedReference<? extends TwinAttributeMapped<Definition>> mapDefinitionReference(MappingContext context, Classifier definition) throws MappingException {
		return context.mapReference(definition, getRawClass());
	}

	@Override
	public Optional<Reference<? extends TwinAttribute<Definition>>> getDefinitionOfUsage() {
		return Optional.ofNullable(typeReference);
	}

	@Override
	public List<Reference<? extends TwinAttribute<Definition>>> getSuperTypeOfDefinitions() {
		return List.copyOf(superTypes);
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
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Optional<TwinExpression> getExpression() {
		return Optional.ofNullable(expression);
	}
}