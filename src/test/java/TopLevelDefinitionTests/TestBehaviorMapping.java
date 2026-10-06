package TopLevelDefinitionTests;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.example.Mapping.Model.Action.TwinActionUsage;
import org.example.Mapping.Model.Action.TwinAssignmentUsage;
import org.example.Mapping.Model.Action.TwinForLoopUsage;
import org.example.Mapping.Model.Action.TwinIfElseUsage;
import org.example.Mapping.Model.Action.TwinTransitionUsage;
import org.example.Mapping.Model.Action.TwinWhileUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Expression.TwinCalculationUsage;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Expression.TwinReferenceUsage;
import org.example.Mapping.Model.Function.CustomCalculationDefinition;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBehaviorMapping extends AbstarctTest {

	@Test
	public void testControlUnitComplete() {

		TwinStateMachineUsage controlUnit = named(TwinStateMachineUsage.class, "cm1");

		assertEquals("cm1", controlUnit.getName());
		assertNotNull(controlUnit.getId());

		assertParent(controlUnit, PhysicalTwinUsage.class, "physicalBattery");

		assertEquals(0, controlUnit.getLocalAttributes().size());

		assertEquals(3, controlUnit.getInputs().size());
		assertEquals(1, controlUnit.getOutputs().size());

		assertTrue(controlUnit.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertEquals(2, controlUnit.getStates().size());

		TwinStateMachineUsage idle = controlUnit.getStates().stream().filter(x -> "idle".equals(x.getName())).findFirst().orElseThrow();

		TwinStateMachineUsage charging = controlUnit.getStates().stream().filter(x -> "charging".equals(x.getName())).findFirst().orElseThrow();

		assertEquals(1, charging.getStates().size());

		TwinStateMachineUsage test34 = charging.getStates().getFirst();

		assertEquals("test34", test34.getName());

		assertInstanceOf(TwinAssignmentUsage.class, charging.getEntryAction().orElseThrow());

		assertInstanceOf(TwinAssignmentUsage.class, charging.getDoAction().orElseThrow());

		assertInstanceOf(TwinAssignmentUsage.class, charging.getExitAction().orElseThrow());

		assertAssignmentTo(assertInstanceOf(TwinAssignmentUsage.class, charging.getEntryAction().orElseThrow()), "charge");

		assertAssignmentTo(assertInstanceOf(TwinAssignmentUsage.class, charging.getDoAction().orElseThrow()), "charge");

		assertAssignmentTo(assertInstanceOf(TwinAssignmentUsage.class, charging.getExitAction().orElseThrow()), "charge");

		assertInstanceOf(TwinAssignmentUsage.class, test34.getEntryAction().orElseThrow());

		assertInstanceOf(TwinAssignmentUsage.class, test34.getDoAction().orElseThrow());

		assertInstanceOf(TwinAssignmentUsage.class, test34.getExitAction().orElseThrow());

		TwinAssignmentUsage test34Entry = assertInstanceOf(TwinAssignmentUsage.class, test34.getEntryAction().orElseThrow());

		assertEquals("charge", test34Entry.getReferent().getName());

		TwinReferenceUsage<?> reference = assertInstanceOf(TwinReferenceUsage.class, test34Entry.getValue());

		assertEquals("temp", reference.getTarget().getName());

		assertEquals(3, controlUnit.getTransitions().size());

		for (TwinTransitionUsage transition : controlUnit.getTransitions()) {

			assertNotNull(transition.getSource());

			assertNotNull(transition.getTarget());

			assertFalse(transition.getGuard().isEmpty());
		}
	}

	@Test
	public void testControlUnitTransitions() {

		TwinStateMachineUsage controlUnit = named(TwinStateMachineUsage.class, "cm1");

		List<TwinTransitionUsage> transitions = controlUnit.getTransitions();

		assertEquals(3, transitions.size());

		boolean idleToCharging = false;
		boolean idleToIdle = false;
		boolean chargingToIdle = false;

		for (TwinTransitionUsage transition : transitions) {

			AbstractModel<?> source = transition.getSource();

			AbstractModel<?> target = transition.getTarget();

			String sourceName = source.getName();

			String targetName = target.getName();

			if ("idle".equals(sourceName) && "charging".equals(targetName)) {

				idleToCharging = true;

				assertEquals(1, transition.getGuard().size());

				assertInstanceOf(TwinReferenceUsage.class, transition.getGuard().getFirst());
			}

			if ("idle".equals(sourceName) && "idle".equals(targetName)) {

				idleToIdle = true;

				assertCalculationGuard(transition.getGuard());
			}

			if ("charging".equals(sourceName) && "idle".equals(targetName)) {

				chargingToIdle = true;

				assertCalculationGuard(transition.getGuard());
			}
		}

		assertTrue(idleToCharging);
		assertTrue(idleToIdle);
		assertTrue(chargingToIdle);
	}

	@Test
	public void testDescriptiveStateMachineComplete() {

		TwinStateMachineUsage machine = named(TwinStateMachineUsage.class, "test12");

		assertParent(machine, DescriptiveModelUsage.class, "descriptiveBattery");

		assertEquals(2, machine.getStates().size());

		assertTrue(machine.getStates().stream().anyMatch(x -> "sa".equals(x.getName())));

		assertTrue(machine.getStates().stream().anyMatch(x -> "sd".equals(x.getName())));
	}

	@Test
	public void testDescriptiveStrategyComplete() {

		TwinStrategyUsage<?, ?> strategy = named(TwinStrategyUsage.class, "LLM_Request");

		assertParent(strategy, DescriptiveModelUsage.class, "descriptiveBattery");

		List<TwinAttributeUsage<?, ?>> inputs = strategy.getInputs();

		List<TwinAttributeUsage<?, ?>> outputs = strategy.getOutputs();

		assertEquals(3, inputs.size());

		assertTrue(inputs.stream().anyMatch(x -> "avgTemperature".equals(x.getName())));

		assertTrue(inputs.stream().anyMatch(x -> "current".equals(x.getName())));

		assertTrue(inputs.stream().anyMatch(x -> "soc".equals(x.getName())));


		for (TwinAttributeUsage<?, ?> input : inputs) {
			assertTrue(input.getExpression().isEmpty());
		}

		assertEquals(1, outputs.size());

		TwinAttributeUsage<?, ?> llmCurrent = outputs.getFirst();

		assertEquals("llmCurrent", llmCurrent.getName());

		assertTrue(llmCurrent.getExpression().isEmpty());
	}

	@Test
	public void testPredictiveStrategyComplete() {

		TwinStrategyUsage<?, ?> strategy = named(TwinStrategyUsage.class, "consForecast");

		assertParent(strategy, PredictiveModelUsage.class, "predictiveBattery");

		assertEquals(2, strategy.getInputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "avgTemperature".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "current".equals(x.getName())));

		for (TwinAttributeUsage<?, ?> input : strategy.getInputs()) {

			assertTrue(input.getExpression().isEmpty());
		}

		assertEquals(1, strategy.getOutputs().size());

		TwinAttributeUsage<?, ?> predicted = strategy.getOutputs().getFirst();

		assertEquals("predicted", predicted.getName());

		assertTrue(predicted.getExpression().isEmpty());
	}

	@Test
	public void testPrescriptiveStrategyInternalComplete() {

		TwinStrategyUsage<?, ?> strategy = named(TwinStrategyUsage.class, "chargeStrategyInternal");

		assertParent(strategy, PrescriptiveModelUsage.class, "prescriptiveBattery");

		assertEquals(3, strategy.getInputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "predictedCurrent".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "posTest12".equals(x.getName())));

		assertEquals(1, strategy.getOutputs().size());

		TwinAttributeUsage<?, ?> chargeCmd = strategy.getOutputs().getFirst();

		assertEquals("chargeCmd", chargeCmd.getName());


		assertTrue(chargeCmd.getExpression().isEmpty());
		assertTrue(strategy.getActions().size() ==1);


		TwinActionUsage<?, ?, ?> t = strategy.getActions().getFirst();
		assertInstanceOf(TwinIfElseUsage.class, t);
		TwinIfElseUsage ifElse = assertInstanceOf(TwinIfElseUsage.class, t);


	}

	@Test
	public void testPrescriptiveStrategyExternalComplete() {

		TwinStrategyUsage<?, ?> strategy = named(TwinStrategyUsage.class, "chargeStrategyExternal");

		assertParent(strategy, PrescriptiveModelUsage.class, "prescriptiveBattery");

		assertEquals(2, strategy.getInputs().size());

		assertEquals(1, strategy.getOutputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "predictedCurrent".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertEquals("chargeCmd", strategy.getOutputs().getFirst().getName());
	}

	@Test
	public void testAvgCustomCalculationActionsComplete() {

		CustomCalculationDefinition avg = named(CustomCalculationDefinition.class, "Avg");

		assertEquals(1, avg.getInputs().size());

		assertEquals("reals", avg.getInputs().getFirst().getName());

		assertEquals(1, avg.getOutputs().size());

		assertEquals("avg", avg.getOutputs().getFirst().getName());

		TwinActionUsage<?, ?, ?> testAction = avg.getActions().stream().filter(x -> "test".equals(x.getName())).findFirst().orElseThrow();

		TwinActionBlockUsage<?, ?> testBlock = assertInstanceOf(TwinActionBlockUsage.class, testAction);

		assertEquals(0, testBlock.getLocalAttributes().size());

		TwinForLoopUsage forLoop = testBlock.getActions().stream().filter(TwinForLoopUsage.class::isInstance).map(TwinForLoopUsage.class::cast).findFirst().orElseThrow();

		assertTrue(forLoop.getLoopVariable().isPresent());

		assertEquals("value", forLoop.getLoopVariable().orElseThrow().getName());

		assertTrue(forLoop.getCollection().isPresent());

		TwinActionBlockUsage<?, ?> forBody = assertInstanceOf(TwinActionBlockUsage.class, forLoop.getBody().orElseThrow());

		assertEquals(1, forBody.getLocalAttributes().size());

		TwinIfElseUsage ifElse = forBody.getActions().stream().filter(TwinIfElseUsage.class::isInstance).map(TwinIfElseUsage.class::cast).findFirst().orElseThrow();

		assertTrue(ifElse.getCondition().isPresent());

		assertTrue(ifElse.getThenAction().isPresent());

		TwinActionBlockUsage<?, ?> elseBlock = assertInstanceOf(TwinActionBlockUsage.class, ifElse.getElseAction().orElseThrow());

		TwinWhileUsage whileLoop = elseBlock.getActions().stream().filter(TwinWhileUsage.class::isInstance).map(TwinWhileUsage.class::cast).findFirst().orElseThrow();

		assertTrue(whileLoop.getCondition().isPresent());

		TwinActionBlockUsage<?, ?> whileBody = assertInstanceOf(TwinActionBlockUsage.class, whileLoop.getBody().orElseThrow());

		assertTrue(whileBody.getActions().stream().anyMatch(x -> "test".equals(x.getName())));


		assertEquals(0, whileBody.getSuccessions().size());


		assertFalse(result.getByType(TwinAssignmentUsage.class).isEmpty());

		for (TwinAssignmentUsage assignment : result.getByType(TwinAssignmentUsage.class)) {

			assertNotNull(assignment.getReferent());

			assertNotNull(assignment.getValue());
		}
	}

	@Test
	public void testAvgDerivedAttributeAssignment() {

		TwinAttributeUsage<?, ?> avgTemp = named(TwinAttributeUsage.class, "avgTemp");

		assertTrue(avgTemp.getExpression().isEmpty());


		TwinAssignmentUsage assignment = originals(TwinAssignmentUsage.class).stream().filter(x -> x.getReferent() != null && "avgTemp".equals(x.getReferent().getName())).findFirst().orElseThrow();

		TwinCalculationUsage avgCall = assertInstanceOf(TwinCalculationUsage.class, assignment.getValue());

		assertEquals(1, avgCall.getArguments().size());

		TwinExpressionUsage argument = avgCall.getArguments().getFirst();

		TwinReferenceUsage<?> reference = assertInstanceOf(TwinReferenceUsage.class, argument);

		assertEquals("temps", reference.getTarget().getName());
	}

	@Test
	public void testLocalFeatureChainExpression() {


		TwinAttributeUsage<?, ?> test12 = named(TwinAttributeUsage.class, "test12");

		assertTrue(test12.getExpression().isPresent());

		TwinExpressionUsage expression = test12.getExpression().orElseThrow();

		TwinCalculationUsage avgCall = assertInstanceOf(TwinCalculationUsage.class, expression);

		assertEquals(1, avgCall.getArguments().size());

		TwinExpressionUsage argument = avgCall.getArguments().getFirst();

		TwinReferenceUsage<?> reference = assertInstanceOf(TwinReferenceUsage.class, argument);

		assertEquals("x", reference.getTarget().getName());
	}

	private void assertAssignmentTo(TwinAssignmentUsage assignment, String targetName) {

		assertNotNull(assignment.getReferent());

		assertEquals(targetName, assignment.getReferent().getName());

		assertNotNull(assignment.getValue());
	}

	private void assertCalculationGuard(List<TwinExpressionUsage> guards) {

		assertEquals(1, guards.size());

		TwinCalculationUsage calculation = assertInstanceOf(TwinCalculationUsage.class, guards.getFirst());

		assertFalse(calculation.getArguments().isEmpty());
	}
}
