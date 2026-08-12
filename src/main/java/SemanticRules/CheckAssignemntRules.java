package SemanticRules;

import org.example.Mapping.Interfaces.*;
import org.example.TwinDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckAssignemntRules implements SemanticRule {

	@Override
	public boolean isValid(TwinDataBase database) throws SemanticException {

		Set<ControlUnit> controlUnits =
				database.get(ControlUnit.class);

		for (ControlUnit controlUnit : controlUnits) {

			checkAll(
					controlUnit.getLocalAttributes(),
					controlUnit
			);

			for (StateMachine state : controlUnit.getStates()) {
				checkStateRecursive(
						controlUnit.getLocalAttributes(),
						state
				);
			}
		}

		return true;
	}

	private void checkStateRecursive(
			List<TwinAttribute> parentAvailableAttributes,
			StateMachine state
	) throws SemanticException {

		List<TwinAttribute> availableAttributes =
				new ArrayList<>(parentAvailableAttributes);

		availableAttributes.addAll(
				state.getLocalAttributes()
		);

		checkAll(
				availableAttributes,
				state
		);

		for (StateMachine child : state.getStates()) {
			checkStateRecursive(
					availableAttributes,
					child
			);
		}
	}

	public void checkAll(
			List<TwinAttribute> availableAttributes,
			StateMachine stateMachine
	) throws SemanticException {

		List<Action> actions =
				new ArrayList<>();

		if (stateMachine.getEntryAction() != null) {
			actions.add(
					stateMachine.getEntryAction()
			);
		}

		if (stateMachine.getExitAction() != null) {
			actions.add(
					stateMachine.getExitAction()
			);
		}

		if (stateMachine.getDoAction() != null) {
			actions.add(
					stateMachine.getDoAction()
			);
		}

		actions.addAll(
				stateMachine.getTransitions()
						.stream()
						.map(Transition::getEffectAction)
						.filter(action -> action != null)
						.toList()
		);

		checkActionAssignments(
				availableAttributes,
				actions
		);

		stateMachine.getTransitions()
				.stream()
				.map(Transition::getGuard)
				.filter(guard -> guard != null)
				.forEach(guard -> {
					try {
						for (var g: guard) {
							checkRemainingExpression(availableAttributes, g);
						}
					} catch (SemanticException e) {
						throw new RuntimeException(e);
					}
				});
	}

	private void checkActionAssignments(
			List<TwinAttribute> availableAttributes,
			List<Action> actions
	) throws SemanticException {

		for (Action action : actions) {

			if (action instanceof Assignment assignment) {
				checkActionAssignment(
						availableAttributes,
						assignment
				);
			}
		}
	}

	private void checkActionAssignment(
			List<TwinAttribute> availableAttributes,
			Assignment assignment
	) throws SemanticException {

		TwinAttribute assignedAttribute =
				assignment.getTarget()
						.getReferent();

		boolean assignedCorrect =
				availableAttributes.contains(
						assignedAttribute
				)
						|| parentInstanceOf(
						assignedAttribute,
						Actuators.class
				);

		if (!assignedCorrect) {
			throw new SemanticException(
					"Assignments can only be made to local attributes or actuator attributes."
			);
		}

		checkRemainingExpression(
				availableAttributes,
				assignment.getValue()
		);
	}

	private void checkRemainingExpression(
			List<TwinAttribute> availableAttributes,
			Expression expression
	) throws SemanticException {

		if (expression == null) {
			return;
		}

		switch (expression) {

			case Calculation calculation -> {

				for (Expression argument :
						calculation.getArguments()) {

					checkRemainingExpression(
							availableAttributes,
							argument
					);
				}
			}

			case ConstructorCall constructorCall -> {

				for (Expression argument :
						constructorCall.getArguments()) {

					checkRemainingExpression(
							availableAttributes,
							argument
					);
				}
			}

			case FeatureReference reference -> {

				TwinAttribute attribute =
						reference.getTarget()
								.getReferent();

				checkReadableAttribute(
						availableAttributes,
						attribute
				);
			}

			default -> {
				return;
			}
		}
	}

	private void checkReadableAttribute(
			List<TwinAttribute> availableAttributes,
			TwinAttribute attribute
	) throws SemanticException {

		if (availableAttributes.contains(attribute)) {
			return;
		}

		if (parentInstanceOf(
				attribute,
				Sensors.class
		)) {
			return;
		}

		if (parentInstanceOf(
				attribute,
				Actuators.class
		)) {
			return;
		}

		throw new SemanticException(
				"Assignments can only read from local, sensor or actuator attributes."
		);
	}

	public boolean parentInstanceOf(
			Model model,
			Class<? extends Model> clazz
	) {

		if (model == null) {
			return false;
		}

		if (model.getParent().isEmpty()) {
			return false;
		}

		return clazz.isInstance(
				model.getParent().get()
		);
	}
}