package org.example.SemanticRules;


import Executor.SemanticException;
import Executor.SemanticRule;
import Main.ResultConverter;
import Mapper.NewUtil;
import Model.AbstractType;
import Model.Slots;
import org.example.Mapping.Role;
import org.example.Mapping.Model.Action.TwinAssignmentUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import Model.Predefined.MetaClasses.Expression.ExpressionUsage;
import Model.Predefined.MetaClasses.Expression.InvocationUsage;
import Model.Predefined.MetaClasses.Expression.ReferenceUsage;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class CheckAssignemntRules extends SemanticRule {

	private final Class<TwinAttributeUsage<?, ?>> attributeClass = Slots.rawClassOf(TwinAttributeUsage.class);
	private final Class<ReferenceUsage<?>> referenceClass = Slots.rawClassOf(ReferenceUsage.class);
	private final Class<InvocationUsage<?>> invocationClass = Slots.rawClassOf(InvocationUsage.class);

	public CheckAssignemntRules(NewUtil newUtil) {
		super(newUtil);
	}

	@Override
	public boolean isValid(ResultConverter resultConverter) throws SemanticException {
		for (TwinAttributeUsage<?, ?> attribute : resultConverter.getByType(attributeClass)) {
			if (!attribute.isLibrary()) {
				validateAttributeExpression(attribute);
			}
		}
		for (TwinAssignmentUsage assignment : resultConverter.getByType(TwinAssignmentUsage.class)) {
			if (!assignment.isLibrary()) {
				validateAssignment(assignment);
			}
		}
		return true;
	}

	private void validateAttributeExpression(TwinAttributeUsage<?, ?> attribute) throws SemanticException {
		Optional<ExpressionUsage<?>> expression = attribute.getExpression();
		if (expression.isEmpty()) {
			return;
		}
		Set<Role> roles = attribute.getRoles();
		String owner = "Attribute='%s'".formatted(attribute.getName());

		if (roles.contains(Role.CONST)) {
			validateExpression(expression.get(), referenced -> referenced.contains(Role.CONST), "CONST expressions may only reference CONST attributes.", owner);
			return;
		}
		if (roles.isEmpty()) {
			validateExpression(expression.get(), referenced -> false, "CONFIG expressions must not contain feature references.", owner);
			return;
		}
		if (roles.contains(Role.LOCAL)) {
			validateExpression(expression.get(), this::isLocalReferenceAllowed, "LOCAL expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.", owner);
			return;
		}
		throw new SemanticException("Attribute '%s' with roles '%s' must not have an expression.".formatted(attribute.getName(), roles));
	}

	private void validateAssignment(TwinAssignmentUsage assignment) throws SemanticException {
		TwinAttributeUsage<?, ?> target = assignment.getReferent();
		if (!isLocalReferenceAllowed(target.getRoles())) {
			throw new SemanticException(("Assignment target '%s' has roles '%s'. Assignment targets must be LOCAL, ACTION or FOR_LOOP_VARIABLE.")
					.formatted(target.getName(), target.getRoles()));
		}
		validateExpression(assignment.getValue(), this::isLocalReferenceAllowed,
				"Assignment value expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.",
				"Assignment target='%s'".formatted(target.getName()));
	}

	private void validateExpression(ExpressionUsage<?> expression, Predicate<Set<Role>> rule, String message, String owner) throws SemanticException {
		if (expression == null) {
			return;
		}
		if (referenceClass.isInstance(expression)) {
			Set<Role> roles = effectiveRoles(referenceClass.cast(expression).getTarget());
			if (!rule.test(roles)) {
				throw new SemanticException("%s %s, referencedRoles='%s'.".formatted(message, owner, roles));
			}
			return;
		}
		if (invocationClass.isInstance(expression)) {
			for (ExpressionUsage<?> argument : invocationClass.cast(expression).getArguments()) {
				validateExpression(argument, rule, message, owner);
			}
		}
	}

	private Set<Role> effectiveRoles(AbstractType<?, ?> target) {
		Set<Role> roles = EnumSet.noneOf(Role.class);
		for (AbstractType<?, ?> current = target; attributeClass.isInstance(current); current = current.getParent().orElse(null)) {
			roles.addAll(attributeClass.cast(current).getRoles());
		}
		return roles;
	}

	private boolean isLocalReferenceAllowed(Set<Role> roles) {
		return roles.contains(Role.LOCAL) || roles.contains(Role.ACTION) || roles.contains(Role.FOR_LOOP_VARIABLE);
	}
}
