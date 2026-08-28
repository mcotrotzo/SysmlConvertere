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
import org.example.Mapping.TwinExpression.TwinOperatorExpression;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

public final class ExpressionRoleValidator {

	private ExpressionRoleValidator() {}

	public static void validateAttributeExpression(
			TwinAttribute<?> attribute,
			TwinExpression expression) throws MappingException {

		if (expression == null) return;

		if (hasRole(attribute, Role.CONST)) {
			validateExpression(
					expression,
					roles -> roles.contains(Role.CONST),
					"CONST expressions may only reference CONST attributes.",
					"Attribute='%s'".formatted(attribute.getName())
			);
			return;
		}

		if (isConfig(attribute)) {
			validateExpression(
					expression,
					roles -> false,
					"CONFIG expressions must not contain feature references.",
					"Attribute='%s'".formatted(attribute.getName())
			);
			return;
		}

		if (isLocalRole(attribute)) {
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
								attribute.getRoleClasses()
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

		if (!isAssignmentRole(target)) {
			throw new MappingException(
					("Assignment target '%s' has roles '%s'. " +
							"Assignment targets must be LOCAL, ACTION or FOR_LOOP_VARIABLE.")
							.formatted(
									target.getName(),
									target.getRoleClasses()
							)
			);
		}
	}

	private static boolean isLocalReferenceAllowed(Set<Role> roles) {
		return roles.contains(Role.LOCAL)
				|| roles.contains(Role.ACTION)
				|| roles.contains(Role.FOR_LOOP_VARIABLE);
	}

	private static boolean isLocalRole(Type<?> attribute) {
		return hasRole(attribute, Role.LOCAL);
	}

	private static boolean isAssignmentRole(Type<?> attribute) {
		return hasAnyRole(
				attribute,
				Role.LOCAL,
				Role.ACTION,
				Role.FOR_LOOP_VARIABLE
		);
	}

	private static boolean hasRole(
			Type<?> attribute,
			Role role) {

		return attribute.getRoleClasses().stream()
				.anyMatch(roleClass ->
						roleClass.additionalRole().contains(role));
	}

	private static boolean hasAnyRole(
			Type<?> attribute,
			Role... roles) {

		return attribute.getRoleClasses().stream()
				.flatMap(roleClass ->
						roleClass.additionalRole().stream())
				.anyMatch(actual ->
						Arrays.asList(roles).contains(actual));
	}

	private static boolean isConfig(Type<?> attribute) {

		return attribute.getRoleClasses().stream()
				.allMatch(roleClass ->
						roleClass.additionalRole().isEmpty());
	}

	private static void validateExpression(
			TwinExpression expression,
			Predicate<Set<Role>> rule,
			String message,
			String owner) throws MappingException {

		if (expression == null) return;


		if (expression instanceof FeatureReference reference) {
			validateFeatureReference(
					reference,
					rule,
					message,
					owner
			);
			return;
		}


		if (expression instanceof TwinOperatorExpression operator) {

			Reference<? extends BaseFunction> function =
					operator.getCalledFunction();

			if (function != null
					&& function.getReferent() != null
					&& function.getReferent()
					.getFunctionKind()
					.equals(BaseFunctionKind.CHAIN)) {

				Set<Role> chainRoles = EnumSet.noneOf(Role.class);

				for (var argument : operator.getArguments()) {
					System.out.println(
							"CHAIN ARG: class=" + argument.getClass().getName()
					);

					if (argument instanceof FeatureReference reference) {
						for (var ref : reference.getChain()) {
							System.out.println(
									"  ref=" + ref.getReferent().getName()
											+ " roles=" + ref.getReferent().getRoleClasses()
							);
						}
					}
				}

				for (var argument : operator.getArguments()) {
					collectChainRoles(argument, chainRoles);
				}

				if (!rule.test(chainRoles)) {
					throw new MappingException(
							"%s %s, chainRoles='%s'."
									.formatted(
											message,
											owner,
											chainRoles
									)
					);
				}

				return;
			}
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

			for (var chainReference : reference.getChain()) {

				var target = chainReference.getReferent();

				target.getRoleClasses()
						.forEach(roleClass ->
								roles.addAll(
										roleClass.additionalRole()
								)
						);
			}

			return;
		}

		if (expression instanceof TwinOperatorExpression operator) {

			Reference<? extends BaseFunction> function =
					operator.getCalledFunction();

			if (function != null
					&& function.getReferent() != null
					&& function.getReferent()
					.getFunctionKind()
					.equals(BaseFunctionKind.CHAIN)) {

				for (var argument : operator.getArguments()) {
					collectChainRoles(
							argument,
							roles
					);
				}
			}
		}
	}


	private static void validateFeatureReference(
			FeatureReference reference,
			Predicate<Set<Role>> rule,
			String message,
			String owner) throws MappingException {

		Set<Role> roles = EnumSet.noneOf(Role.class);

		for (var chainReference : reference.getChain()) {

			var target = chainReference.getReferent();

			target.getRoleClasses()
					.forEach(roleClass ->
							roles.addAll(
									roleClass.additionalRole()
							)
					);
		}

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
}