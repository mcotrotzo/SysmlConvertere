package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Direction;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedElementUsage;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeDefinitionMapped;
import org.example.Mapping.TwinExpression.TwinExpression;
import org.example.Mapping.TwinExpression.TwinInvocationExpression;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Expression;
import org.omg.sysml.lang.sysml.Feature;

import java.util.Optional;
import java.util.function.Predicate;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeUsageMapped
        extends MappedElementUsage<Feature>
        implements TwinAttributeUsage {

    protected MappedReference<TwinAttributeDefinitionMapped> typeReference;
	private Role role;
	private TwinExpression<?> expression;


    public TwinAttributeUsageMapped(Feature sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public Reference<? extends TwinAttributeDefinition> getDefinition() {
        return typeReference;
    }

	@Override
	public void parse(MappingContext context) throws MappingException {
		expression = context
				.mapOwned(this, Expression.class, TwinExpression.class)
				.stream()
				.findFirst()
				.orElse(null);

		Classifier definition = getSysmlElement()
				.getType()
				.stream()
				.filter(Classifier.class::isInstance)
				.map(Classifier.class::cast)
				.findFirst()
				.orElseThrow(() ->
						new MappingException(
								"No twin attribute definition found for '%s'."
										.formatted(getName())
						)
				);

		typeReference = context.mapReference(
				definition,
				TwinAttributeDefinitionMapped.class
		);
	}



	@Override
	public Role getRole() {
		return role;
	}

	@Override
	public Optional<org.example.Mapping.Interfaces.TwinExpression.TwinExpression> getExpression() {
		return Optional.ofNullable(expression);
	}

	@Override
	public Direction getDirection() {
		return switch (this.getSysmlElement().getDirection()) {
			case IN -> Direction.IN;
			case OUT -> Direction.OUT;
			case INOUT -> Direction.INOUT;
		};
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public void postValidate() throws MappingException {
		super.postValidate();

		if (expression == null) {
			return;
		}
		switch (getRole()) {
			case CONST -> checkConstRules();
			case CONFIG -> checkConfigRules();
			case LOCAL -> checkLocalRules();

			default -> throw new MappingException(
					"Attribute with role '%s' must not have an expression."
							.formatted(getRole())
			);
		}
	}

	private void checkConfigRules() throws MappingException {
		validateExpression(
				expression,
				reference -> false,
				"CONFIG expressions must not contain feature references."
		);
	}

	private void checkConstRules() throws MappingException {
		validateExpression(
				expression,
				reference ->
						reference.getTarget()
								.getReferent()
								.getRole() == Role.CONST,
				"CONST expressions may only reference CONST attributes."
		);
	}

	private void checkLocalRules() throws MappingException {
		validateExpression(
				expression,
				reference -> switch (
						reference.getTarget()
								.getReferent()
								.getRole()
						) {
					case LOCAL, ACTION, FOR_LOOP_VARIABLE -> true;
					default -> false;
				},
				"LOCAL expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes."
		);
	}

	private void validateExpression(
			org.example.Mapping.Interfaces.TwinExpression.TwinExpression expression,
			Predicate<FeatureReference> rule,
			String message
	) throws MappingException {

		if (expression instanceof FeatureReference reference) {
			if (!rule.test(reference)) {
				throw new MappingException(message);
			}
		}

		if (expression instanceof TwinInvocationExpression<?,?> invocation) {
			for (var argument : invocation.getArguments()) {
				validateExpression(argument, rule, message);
			}
		}
	}





}
