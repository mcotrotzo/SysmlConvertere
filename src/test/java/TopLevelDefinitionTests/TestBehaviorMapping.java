package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModel;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.BaseTaxonomy.PredictiveModel;
import org.example.Mapping.Interfaces.BaseTaxonomy.PrescriptiveModel;
import org.example.Mapping.Interfaces.TwinAction.*;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBehaviorMapping extends AbstarctTest {

	@Test
	public void testControlUnitComplete() {

		TwinStateMachine<Usage> controlUnit = named(TwinStateMachine.class, Usage.class, "cm1");

		assertEquals("cm1", controlUnit.getName());
		assertNotNull(controlUnit.getId());

		assertParent(controlUnit, PhysicalTwin.class, Usage.class, "physicalBattery");

		assertEquals(0, controlUnit.localAttributes().size());

		assertEquals(3, controlUnit.getInputs().size());
		assertEquals(1, controlUnit.getOutputs().size());

		assertTrue(controlUnit.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertEquals(2, controlUnit.getStates().size());

		TwinStateMachine<Usage> idle = controlUnit.getStates().stream().filter(x -> "idle".equals(x.getName())).findFirst().orElseThrow();

		TwinStateMachine<Usage> charging = controlUnit.getStates().stream().filter(x -> "charging".equals(x.getName())).findFirst().orElseThrow();

		assertEquals(1, charging.getStates().size());

		TwinStateMachine<Usage> test34 = charging.getStates().getFirst();

		assertEquals("test34", test34.getName());

		assertInstanceOf(Assignment.class, charging.getEntryAction());

		assertInstanceOf(Assignment.class, charging.getDoAction());

		assertInstanceOf(Assignment.class, charging.getExitAction());

		assertAssignmentTo((Assignment) charging.getEntryAction(), "charge");

		assertAssignmentTo((Assignment) charging.getDoAction(), "charge");

		assertAssignmentTo((Assignment) charging.getExitAction(), "charge");

		assertInstanceOf(Assignment.class, test34.getEntryAction());

		assertInstanceOf(Assignment.class, test34.getDoAction());

		assertInstanceOf(Assignment.class, test34.getExitAction());

		Assignment test34Entry = (Assignment) test34.getEntryAction();

		assertEquals("charge", test34Entry.getTarget().getReferent().getName());

		assertInstanceOf(FeatureReference.class, test34Entry.getValue());

		FeatureReference reference = (FeatureReference) test34Entry.getValue();

		assertEquals("temp", reference.getTarget().getReferent().getName());

		assertEquals(3, controlUnit.getTransitions().size());

		for (Transition transition : controlUnit.getTransitions()) {

			assertNotNull(transition.getSource());

			assertNotNull(transition.getTarget());

			assertFalse(transition.getGuard().isEmpty());
		}
	}

	@Test
	public void testControlUnitTransitions() {

		TwinStateMachine<Usage> controlUnit = named(TwinStateMachine.class, Usage.class, "cm1");

		List<Transition> transitions = controlUnit.getTransitions();

		assertEquals(3, transitions.size());

		boolean idleToCharging = false;
		boolean idleToIdle = false;
		boolean chargingToIdle = false;

		for (Transition transition : transitions) {

			Model<?> source = transition.getSource().getReferent();

			Model<?> target = transition.getTarget().getReferent();

			String sourceName = source.getName();

			String targetName = target.getName();

			if ("idle".equals(sourceName) && "charging".equals(targetName)) {

				idleToCharging = true;

				assertEquals(1, transition.getGuard().size());

				assertInstanceOf(FeatureReference.class, transition.getGuard().getFirst());
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

		TwinStateMachine<Usage> machine = named(TwinStateMachine.class, Usage.class, "test12");

		assertParent(machine, DescriptiveModel.class, Usage.class, "descriptiveBattery");

		assertEquals(2, machine.getStates().size());

		assertTrue(machine.getStates().stream().anyMatch(x -> "sa".equals(x.getName())));

		assertTrue(machine.getStates().stream().anyMatch(x -> "sd".equals(x.getName())));
	}

	@Test
	public void testDescriptiveStrategyComplete() {

		Strategy<Usage> strategy = named(Strategy.class, Usage.class, "LLM_Request");

		assertParent(strategy, DescriptiveModel.class, Usage.class, "descriptiveBattery");

		List<TwinAttribute<Usage>> inputs = strategy.getInputs();

		List<TwinAttribute<Usage>> outputs = strategy.getOutputs();

		assertEquals(3, inputs.size());

		assertTrue(inputs.stream().anyMatch(x -> "avgTemperature".equals(x.getName())));

		assertTrue(inputs.stream().anyMatch(x -> "current".equals(x.getName())));

		assertTrue(inputs.stream().anyMatch(x -> "soc".equals(x.getName())));

		/*
		 * Diese Attribute werden über Flows gespeist.
		 * Sie besitzen deshalb selbst keine Expression.
		 */
		for (TwinAttribute<Usage> input : inputs) {
			assertTrue(input.getExpression().isEmpty());
		}

		assertEquals(1, outputs.size());

		TwinAttribute<Usage> llmCurrent = outputs.getFirst();

		assertEquals("llmCurrent", llmCurrent.getName());

		assertTrue(llmCurrent.getExpression().isEmpty());
	}

	@Test
	public void testPredictiveStrategyComplete() {

		Strategy<Usage> strategy = named(Strategy.class, Usage.class, "consForecast");

		assertParent(strategy, PredictiveModel.class, Usage.class, "predictiveBattery");

		assertEquals(2, strategy.getInputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "avgTemperature".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "current".equals(x.getName())));

		for (TwinAttribute<Usage> input : strategy.getInputs()) {

			assertTrue(input.getExpression().isEmpty());
		}

		assertEquals(1, strategy.getOutputs().size());

		TwinAttribute<Usage> predicted = strategy.getOutputs().getFirst();

		assertEquals("predicted", predicted.getName());

		assertTrue(predicted.getExpression().isEmpty());
	}

	@Test
	public void testPrescriptiveStrategyInternalComplete() {

		Strategy<Usage> strategy = named(Strategy.class, Usage.class, "chargeStrategyInternal");

		assertParent(strategy, PrescriptiveModel.class, Usage.class, "prescriptiveBattery");

		assertEquals(3, strategy.getInputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "predictedCurrent".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "posTest12".equals(x.getName())));

		assertEquals(1, strategy.getOutputs().size());

		TwinAttribute<Usage> chargeCmd = strategy.getOutputs().getFirst();

		assertEquals("chargeCmd", chargeCmd.getName());

		/*
		 * ACTION-Output:
		 * Wert wird über assign gesetzt, nicht über
		 * eine Attribute-Expression.
		 */
		assertTrue(chargeCmd.getExpression().isEmpty());
	}

	@Test
	public void testPrescriptiveStrategyExternalComplete() {

		Strategy<Usage> strategy = named(Strategy.class, Usage.class, "chargeStrategyExternal");

		assertParent(strategy, PrescriptiveModel.class, Usage.class, "prescriptiveBattery");

		assertEquals(2, strategy.getInputs().size());

		assertEquals(1, strategy.getOutputs().size());

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "predictedCurrent".equals(x.getName())));

		assertTrue(strategy.getInputs().stream().anyMatch(x -> "maxCharge".equals(x.getName())));

		assertEquals("chargeCmd", strategy.getOutputs().getFirst().getName());
	}

	@Test
	public void testAvgCustomCalculationActionsComplete() {

		CustomCalculation avg = named(CustomCalculation.class, Definition.class, "Avg");

		assertEquals(1, avg.getInputs().size());

		assertEquals("reals", avg.getInputs().getFirst().getName());

		assertEquals(1, avg.getOutputs().size());

		assertEquals("avg", avg.getOutputs().getFirst().getName());

		Action<Usage> testAction = avg.getActions().stream().filter(x -> "test".equals(x.getName())).findFirst().orElseThrow();

		assertInstanceOf(Block.class, testAction);


		Block<?> testBlock = (Block<?>) testAction;
		assertEquals(0, testBlock.localAttributes().size());

		ForLoop forLoop = testBlock.getActions().stream().filter(ForLoop.class::isInstance).map(ForLoop.class::cast).findFirst().orElseThrow();

		assertNotNull(forLoop.getLoopVariable());

		assertEquals("value", forLoop.getLoopVariable().getName());

		assertNotNull(forLoop.getCollection());

		assertInstanceOf(Block.class, forLoop.getBody());

		Block<?> forBody = (Block<?>) forLoop.getBody();
		assertEquals(1, forBody.localAttributes().size());

		IfElse ifElse = forBody.getActions().stream().filter(IfElse.class::isInstance).map(IfElse.class::cast).findFirst().orElseThrow();

		assertNotNull(ifElse.getCondition());

		assertNotNull(ifElse.getThenAction());

		assertInstanceOf(Block.class, ifElse.getElseAction());

		Block<?> elseBlock = (Block<?>) ifElse.getElseAction();

		WhileLoop whileLoop = elseBlock.getActions().stream().filter(WhileLoop.class::isInstance).map(WhileLoop.class::cast).findFirst().orElseThrow();

		assertNotNull(whileLoop.getCondition());

		assertInstanceOf(Block.class, whileLoop.getBody());

		Block<?> whileBody = (Block<?>) whileLoop.getBody();

		assertTrue(whileBody.getActions().stream().anyMatch(x -> "test".equals(x.getName())));


		assertEquals(0, whileBody.getSuccessions().size());


		assertFalse(result.get(Assignment.class, Usage.class).isEmpty());

		for (Assignment assignment : result.get(Assignment.class, Usage.class)) {

			assertNotNull(assignment.getTarget());

			assertNotNull(assignment.getTarget().getReferent());

			assertNotNull(assignment.getValue());
		}
	}

	@Test
	public void testAvgDerivedAttributeAssignment() {

		TwinAttribute<Usage> avgTemp = named(TwinAttribute.class, Usage.class, "avgTemp");

		assertTrue(avgTemp.getExpression().isEmpty());


		Assignment assignment = result.get(Assignment.class, Usage.class).stream().filter(x -> x.getTarget() != null && x.getTarget().getReferent() != null && "avgTemp".equals(x.getTarget().getReferent().getName())).findFirst().orElseThrow();

		assertInstanceOf(Calculation.class, assignment.getValue());

		Calculation avgCall = (Calculation) assignment.getValue();

		assertEquals(1, avgCall.getArguments().size());

		TwinExpression argument = avgCall.getArguments().getFirst();

		assertInstanceOf(FeatureReference.class, argument);

		FeatureReference reference = (FeatureReference) argument;

		assertEquals("temps", reference.getTarget().getReferent().getName());
	}

	@Test
	public void testLocalFeatureChainExpression() {

		/*
		 * attribute test12:TwinReal:>local_Attributes
		 *     = Avg(posTest12.x);
		 *
		 * LOCAL darf eine Expression besitzen.
		 */
		TwinAttribute<Usage> test12 = named(TwinAttribute.class, Usage.class, "test12");

		assertTrue(test12.getExpression().isPresent());

		TwinExpression expression = test12.getExpression().orElseThrow();

		assertInstanceOf(Calculation.class, expression);

		Calculation avgCall = (Calculation) expression;

		assertEquals(1, avgCall.getArguments().size());

		TwinExpression argument = avgCall.getArguments().getFirst();

		assertInstanceOf(FeatureReference.class, argument);

		FeatureReference reference = (FeatureReference) argument;

		assertEquals("x", reference.getTarget().getReferent().getName());
	}

	private void assertAssignmentTo(Assignment assignment, String targetName) {

		assertNotNull(assignment.getTarget());

		assertNotNull(assignment.getTarget().getReferent());

		assertEquals(targetName, assignment.getTarget().getReferent().getName());

		assertNotNull(assignment.getValue());
	}

	private void assertCalculationGuard(List<TwinExpression> guards) {

		assertEquals(1, guards.size());

		assertInstanceOf(Calculation.class, guards.getFirst());

		Calculation calculation = (Calculation) guards.getFirst();

		assertFalse(calculation.getArguments().isEmpty());
	}
}