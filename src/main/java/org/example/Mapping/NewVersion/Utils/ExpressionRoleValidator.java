package org.example.Mapping.NewVersion.Utils;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinExpression.TwinInvocationExpression;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public final class ExpressionRoleValidator {

	private ExpressionRoleValidator() {}

	public static void validateAttributeExpression(
			TwinAttribute<?> attribute,
			TwinExpression expression) throws MappingException {

		if (expression == null) return;

		if (attribute.getRoles().contains(Role.CONST)) {
			validateExpression(
					expression,
					roles -> roles.contains(Role.CONST),
					"CONST expressions may only reference CONST attributes.",
					"Attribute='%s'".formatted(attribute.getName())
			);
			return;
		}

		if (attribute.getRoles().isEmpty()) {
			validateExpression(
					expression,
					roles -> false,
					"CONFIG expressions must not contain feature references.",
					"Attribute='%s'".formatted(attribute.getName())
			);
			return;
		}

		if (attribute.getRoles().contains(Role.LOCAL)) {
			validateExpression(
					expression,
					ExpressionRoleValidator::isLocalReferenceAllowed,
					"LOCAL expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.",
					"Attribute='%s'".formatted(attribute.getName())
			);
			return;
		}

		throw new MappingException(
				"Attribute '%s' with roles '%s' must not have an expression."
						.formatted(
								attribute.getName(),
								attribute.getRoles()
						)
		);
	}

	public static void validateAssignment(
			TwinAttribute<?> assignmentTarget,
			TwinExpression value) throws MappingException {

		validateAssignmentTarget(assignmentTarget);

		validateExpression(
				value,
				ExpressionRoleValidator::isLocalReferenceAllowed,
				"Assignment value expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.",
				"Assignment target='%s'".formatted(assignmentTarget.getName())
		);
	}

	private static void validateAssignmentTarget(
			TwinAttribute<?> target) throws MappingException {

		if (!isAssignmentRole(target.getRoles())) {
			throw new MappingException(
					("Assignment target '%s' has roles '%s'. " +
							"Assignment targets must be LOCAL, ACTION or FOR_LOOP_VARIABLE.")
							.formatted(
									target.getName(),
									target.getRoles()
							)
			);
		}
	}

	private static boolean isLocalReferenceAllowed(Set<Role> roles) {
		return roles.contains(Role.LOCAL)
				|| roles.contains(Role.ACTION)
				|| roles.contains(Role.FOR_LOOP_VARIABLE);
	}

	private static boolean isAssignmentRole(Set<Role> roles) {
		return roles.contains(Role.LOCAL)
				|| roles.contains(Role.ACTION)
				|| roles.contains(Role.FOR_LOOP_VARIABLE);
	}


	private static Set<Role> rolesOf(Type<?> target) {
		if (target instanceof TwinAttribute<?> attribute) {
			return attribute.getRoles();
		}
		return Set.of();
	}

	private static void validateExpression(
			TwinExpression expression,
			Predicate<Set<Role>> rule,
			String message,
			String owner) throws MappingException {

		if (expression == null) return;


		if (expression instanceof FeatureReference reference) {
			validateChain(
					reference.getChain(),
					rule,
					message,
					owner
			);
			return;
		}



		if (expression instanceof TwinInvocationExpression<?> invocation) {

			for (var argument : invocation.getArguments()) {
				validateExpression(
						argument,
						rule,
						message,
						owner
				);
			}
		}
	}


	private static void collectChainRoles(
			TwinExpression expression,
			Set<Role> roles) {

		if (expression == null) return;


		if (expression instanceof FeatureReference reference) {
			roles.addAll(effectiveRoles(reference.getChain()));
			return;
		}

	}


	private static void validateChain(
			List<? extends Reference<? extends Type<Usage>>> chain,
			Predicate<Set<Role>> rule,
			String message,
			String owner) throws MappingException {

		Set<Role> roles = effectiveRoles(chain);

		if (!rule.test(roles)) {
			throw new MappingException(
					"%s %s, referencedRoles='%s'."
							.formatted(
									message,
									owner,
									roles
							)
			);
		}
	}

	private static Set<Role> effectiveRoles(
			List<? extends Reference<? extends Type<Usage>>> chain) {

		Set<Role> roles = EnumSet.noneOf(Role.class);

		for (var chainReference : chain) {
			roles.addAll(rolesOf(chainReference.getReferent()));
		}

		return roles;
	}
}