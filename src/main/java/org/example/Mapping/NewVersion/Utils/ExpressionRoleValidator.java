package org.example.Mapping.NewVersion.Utils;


import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinExpression.TwinInvocationExpression;

import java.util.function.Predicate;

public final class ExpressionRoleValidator {

	private ExpressionRoleValidator() {
	}

	public static void validateAttributeExpression(
			TwinAttribute<?> attribute,
			TwinExpression expression
	) throws MappingException {

		if (expression == null) {
			return;
		}

		switch (attribute.getRole()) {

			case CONST -> validateExpression(
					expression,
					reference ->
							reference.getTarget()
									.getReferent()
									.getRole() == Role.CONST,
					"CONST expressions may only reference CONST attributes.",
					"Attribute='%s'".formatted(attribute.getName())
			);

			case CONFIG -> validateExpression(
					expression,
					reference -> false,
					"CONFIG expressions must not contain feature references.",
					"Attribute='%s'".formatted(attribute.getName())
			);

			case LOCAL -> validateExpression(
					expression,
					ExpressionRoleValidator::isLocalReferenceAllowed,
					"LOCAL expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.",
					"Attribute='%s'".formatted(attribute.getName())
			);

			default -> throw new MappingException(
					"Attribute '%s' with role '%s' must not have an expression."
							.formatted(
									attribute.getName(),
									attribute.getRole()
							)
			);
		}
	}

	public static void validateAssignment(
			TwinAttribute<?> assignmentTarget,
			TwinExpression value
	) throws MappingException {

		validateAssignmentTarget(assignmentTarget);

		validateExpression(
				value,
				ExpressionRoleValidator::isLocalReferenceAllowed,
				"Assignment value expressions may only reference LOCAL, ACTION or FOR_LOOP_VARIABLE attributes.",
				"Assignment target='%s'".formatted(
						assignmentTarget.getName()
				)
		);
	}

	private static void validateAssignmentTarget(
			TwinAttribute<?> target
	) throws MappingException {

		if (!isLocalRole(target.getRole())) {
			throw new MappingException(
					"Assignment target '%s' has role '%s'. "
							+ "Assignment targets must be LOCAL, ACTION or FOR_LOOP_VARIABLE."
							.formatted(
									target.getName(),
									target.getRole()
							)
			);
		}
	}

	private static boolean isLocalReferenceAllowed(
			FeatureReference reference
	) {
		return isLocalRole(
				reference.getTarget()
						.getReferent()
						.getRole()
		);
	}

	private static boolean isLocalRole(Role role) {
		return switch (role) {
			case LOCAL, ACTION, FOR_LOOP_VARIABLE -> true;
			default -> false;
		};
	}

	private static void validateExpression(
			TwinExpression expression,
			Predicate<FeatureReference> rule,
			String message,
			String owner
	) throws MappingException {

		if (expression == null) {
			return;
		}

		if (expression instanceof FeatureReference reference) {

			if (!rule.test(reference)) {
				var target = reference.getTarget().getReferent();

				throw new MappingException(
						"%s %s, referenced='%s', referencedRole='%s'."
								.formatted(
										message,
										owner,
										target.getName(),
										target.getRole()
								)
				);
			}

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
}