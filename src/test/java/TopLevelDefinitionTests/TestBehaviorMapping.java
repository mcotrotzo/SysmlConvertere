package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBehaviorMapping extends AbstarctTest {

	@Test
	public void testControlUnitComplete() {

		ControlUnit controlUnit =
				named(ControlUnit.class, "cm1");

		assertEquals("cm1", controlUnit.getName());
		assertNotNull(controlUnit.getId());

		assertParent(
				controlUnit,
				PhysicalTwin.class,
				"physicalBattery"
		);

		assertEquals(
				1,
				controlUnit.getLocalAttributes().size()
		);

		assertTrue(
				controlUnit.getLocalAttributes()
						.stream()
						.anyMatch(x ->
								"maxCharge".equals(x.getName()))
		);

		assertEquals(
				2,
				controlUnit.getStates().size()
		);

		StateMachine idle =
				controlUnit.getStates()
						.stream()
						.filter(x ->
								"idle".equals(x.getName()))
						.findFirst()
						.orElseThrow();

		StateMachine charging =
				controlUnit.getStates()
						.stream()
						.filter(x ->
								"charging".equals(x.getName()))
						.findFirst()
						.orElseThrow();

		assertNotNull(idle);
		assertNotNull(charging);

		assertEquals(
				1,
				charging.getStates().size()
		);

		StateMachine test34 =
				charging.getStates().get(0);

		assertEquals(
				"test34",
				test34.getName()
		);

		assertTrue(
				charging.getEntryAction()
						instanceof Assignment
		);

		assertTrue(
				charging.getDoAction()
						instanceof Assignment
		);

		assertTrue(
				charging.getExitAction()
						instanceof Assignment
		);

		assertAssignmentTo(
				(Assignment) charging.getEntryAction(),
				"charge"
		);

		assertAssignmentTo(
				(Assignment) charging.getDoAction(),
				"charge"
		);

		assertAssignmentTo(
				(Assignment) charging.getExitAction(),
				"charge"
		);

		assertTrue(
				test34.getEntryAction()
						instanceof Assignment
		);

		assertTrue(
				test34.getDoAction()
						instanceof Assignment
		);

		assertTrue(
				test34.getExitAction()
						instanceof Assignment
		);

		Assignment test34Entry =
				(Assignment) test34.getEntryAction();

		assertEquals(
				"charge",
				test34Entry.getTarget()
						.getReferent()
						.getName()
		);

		assertTrue(
				test34Entry.getValue()
						instanceof FeatureReference
		);

		FeatureReference chain =
				(FeatureReference) test34Entry.getValue();

		assertEquals(
				"temp",
				chain.getTarget()
						.getReferent()
						.getName()
		);

		assertEquals(
				3,
				controlUnit.getTransitions().size()
		);

		for (Transition transition :
				controlUnit.getTransitions()) {

			assertNotNull(
					transition.getSource()
			);

			assertNotNull(
					transition.getTarget()
			);

			assertFalse(
					transition.getGuard().isEmpty()
			);
		}
	}

	@Test
	public void testControlUnitTransitions() {

		ControlUnit controlUnit =
				named(ControlUnit.class, "cm1");

		List<Transition> transitions =
				controlUnit.getTransitions();

		assertEquals(
				3,
				transitions.size()
		);

		boolean idleToCharging = false;
		boolean idleToIdle = false;
		boolean chargingToIdle = false;

		for (Transition transition : transitions) {

			Model source =
					transition.getSource()
							.getReferent();

			Model target =
					transition.getTarget()
							.getReferent();

			assertNotNull(source);
			assertNotNull(target);

			String sourceName =
					source.getName();

			String targetName =
					target.getName();

			if ("idle".equals(sourceName)
					&& "charging".equals(targetName)) {

				idleToCharging = true;

				assertEquals(
						1,
						transition.getGuard().size()
				);

				Expression guard =
						transition.getGuard().get(0);

				assertTrue(guard instanceof FeatureReference
				);
			}

			if ("idle".equals(sourceName)
					&& "idle".equals(targetName)) {

				idleToIdle = true;

				assertCalculationGuard(
						transition.getGuard()
				);
			}

			if ("charging".equals(sourceName)
					&& "idle".equals(targetName)) {

				chargingToIdle = true;

				assertCalculationGuard(
						transition.getGuard()
				);
			}
		}

		assertTrue(idleToCharging);
		assertTrue(idleToIdle);
		assertTrue(chargingToIdle);
	}

	@Test
	public void testDescriptiveStateMachineComplete() {

		DescriptiveStateMachine machine =
				named(
						DescriptiveStateMachine.class,
						"test12"
				);

		assertEquals(
				"test12",
				machine.getName()
		);

		assertParent(
				machine,
				DescriptiveModel.class,
				"descriptiveBattery"
		);

		assertEquals(
				2,
				machine.getStates().size()
		);

		assertTrue(
				machine.getStates()
						.stream()
						.anyMatch(x ->
								"sa".equals(x.getName()))
		);

		assertTrue(
				machine.getStates()
						.stream()
						.anyMatch(x ->
								"sd".equals(x.getName()))
		);

		assertEquals(
				1,
				machine.getTriggerConfiguration().size()
		);

		assertTrue(
				machine.getTriggerConfiguration()
						.get(0)
						instanceof TimeBasedConfiguration
		);

		TimeBasedConfiguration timeBasedConfiguration = (TimeBasedConfiguration) machine.getTriggerConfiguration().get(0);
		assertEquals(EnumTimeUnit.MINUTE, timeBasedConfiguration.getTriggerIntervalUnit());
	}

	@Test
	public void testDescriptiveStrategyComplete() {

		DescriptiveStrategy strategy =
				named(
						DescriptiveStrategy.class,
						"LLM_Request"
				);

		List<TwinAttribute> inputs = strategy.getInputs();
		List<TwinAttribute> outputs = strategy.getOutputs();

		assertEquals(
				1,
				strategy.getTriggerConfiguration().size()
		);

		assertTrue(
				strategy.getTriggerConfiguration().getFirst()
						instanceof TimeBasedConfiguration
		);

		TimeBasedConfiguration config =
				(TimeBasedConfiguration)
						strategy.getTriggerConfiguration().getFirst();

		assertEquals(
				EnumTimeUnit.MINUTE,
				config.getTriggerIntervalUnit()
		);


		assertEquals(2, inputs.size());

		TwinAttribute avgTemperature =
				inputs.stream()
						.filter(x -> "avgTemperature".equals(x.getName()))
						.findFirst()
						.orElseThrow();

		TwinAttribute current =
				inputs.stream()
						.filter(x -> "current".equals(x.getName()))
						.findFirst()
						.orElseThrow();


		assertTrue(
				avgTemperature.getTwinExpressions().isPresent()
		);

		Expression avgTemperatureExpression =
				avgTemperature.getTwinExpressions().orElseThrow();

		assertTrue(
				avgTemperatureExpression instanceof FeatureReference
		);

		FeatureReference avgTemperatureReference =
				(FeatureReference) avgTemperatureExpression;

		assertEquals(
				"avgTemp",
				avgTemperatureReference
						.getTarget()
						.getReferent()
						.getName()
		);

		assertTrue(
				current.getTwinExpressions().isPresent()
		);

		Expression currentExpression =
				current.getTwinExpressions().orElseThrow();

		assertTrue(
				currentExpression instanceof FeatureReference
		);

		FeatureReference currentReference =
				(FeatureReference) currentExpression;

		assertEquals(
				"current",
				currentReference
						.getTarget()
						.getReferent()
						.getName()
		);


		assertEquals(1, outputs.size());

		TwinAttribute llmCurrent =
				outputs.getFirst();

		assertEquals(
				"llmCurrent",
				llmCurrent.getName()
		);
	}

	@Test
	public void testPredictiveStrategyComplete() {

		PredictiveStrategy strategy =
				named(
						PredictiveStrategy.class,
						"consForecast"
				);

		assertParent(
				strategy,
				PredictiveModel.class,
				"predictiveBattery"
		);

		assertEquals(
				2,
				strategy.getInputs().size()
		);

		assertTrue(
				strategy.getInputs()
						.stream()
						.anyMatch(x ->
								"avgTemperature".equals(x.getName()))
		);

		assertTrue(
				strategy.getInputs()
						.stream()
						.anyMatch(x ->
								"current".equals(x.getName()))
		);

		assertEquals(
				1,
				strategy.getOutputs().size()
		);

		assertEquals(
				"predicted",
				strategy.getOutputs()
						.get(0)
						.getName()
		);

		assertEquals(
				1,
				strategy.getTriggerConfiguration().size()
		);

		assertTrue(
				strategy.getTriggerConfiguration()
						.get(0)
						instanceof TimeBasedConfiguration
		);
	}

	@Test
	public void testPrescriptiveStrategyComplete() {

		PrescriptiveStrategy strategy =
				named(
						PrescriptiveStrategy.class,
						"chargeStrategy"
				);

		assertParent(
				strategy,
				PrescriptiveModel.class,
				"prescriptiveBattery"
		);

		assertEquals(
				1,
				strategy.getOutputs().size()
		);

		TwinAttribute chargeCmd =
				strategy.getOutputs().get(0);

		assertEquals(
				"chargeCmd",
				chargeCmd.getName()
		);

		assertTrue(
				chargeCmd.getTwinExpressions().isPresent()
		);

		Expression outputExpression =
				chargeCmd.getTwinExpressions().get();

		assertTrue(
				outputExpression instanceof FeatureReference
						|| outputExpression
						instanceof FeatureReference
		);

		assertEquals(
				1,
				strategy.getTriggerConfiguration().size()
		);

		assertTrue(
				strategy.getTriggerConfiguration()
						.get(0)
						instanceof EventBasedConfiguration
		);
		EventBasedConfiguration eventBasedConfiguration = (EventBasedConfiguration) strategy.getTriggerConfiguration().get(0);
		assertTrue(eventBasedConfiguration.getTriggeringAttributes().size() == 1);


	}

	@Test
	public void testAvgCustomCalculationActionsComplete() {

		CustomCalculation avg =
				named(
						CustomCalculation.class,
						"Avg"
				);

		assertEquals(
				1,
				avg.getInputs().size()
		);

		assertEquals(
				"reals",
				avg.getInputs()
						.get(0)
						.getName()
		);

		assertEquals(
				1,
				avg.getOutputs().size()
		);

		assertEquals(
				"avg",
				avg.getOutputs()
						.get(0)
						.getName()
		);

		Action testAction =
				avg.getActions()
						.stream()
						.filter(x ->
								"test".equals(x.getName()))
						.findFirst()
						.orElseThrow();

		assertTrue(
				testAction instanceof Block
		);

		Block testBlock =
				(Block) testAction;


		ForLoop forLoop =
				testBlock.getActions()
						.stream()
						.filter(ForLoop.class::isInstance)
						.map(ForLoop.class::cast)
						.findFirst()
						.orElseThrow();

		assertNotNull(
				forLoop.getLoopVariable()
		);

		assertEquals(
				"value",
				forLoop.getLoopVariable()
						.getName()
		);

		assertNotNull(
				forLoop.getCollection()
		);

		assertNotNull(
				forLoop.getBody()
		);

		assertTrue(
				forLoop.getBody()
						instanceof Block
		);

		Block forBody =
				(Block) forLoop.getBody();

		IfElse ifElse =
				forBody.getActions()
						.stream()
						.filter(IfElse.class::isInstance)
						.map(IfElse.class::cast)
						.findFirst()
						.orElseThrow();

		assertNotNull(
				ifElse.getCondition()
		);

		assertNotNull(
				ifElse.getThenAction()
		);

		assertNotNull(
				ifElse.getElseAction()
		);

		assertTrue(
				ifElse.getElseAction()
						instanceof Block
		);

		Block elseBlock =
				(Block) ifElse.getElseAction();

		WhileLoop whileLoop =
				elseBlock.getActions()
						.stream()
						.filter(WhileLoop.class::isInstance)
						.map(WhileLoop.class::cast)
						.findFirst()
						.orElseThrow();

		assertNotNull(
				whileLoop.getCondition()
		);

		assertNotNull(
				whileLoop.getBody()
		);

		assertTrue(
				whileLoop.getBody()
						instanceof Block
		);

		Block whileBody =
				(Block) whileLoop.getBody();

		assertTrue(
				whileBody.getActions()
						.stream()
						.anyMatch(x ->
								"test5".equals(x.getName()))
		);

		assertTrue(
				whileBody.getActions()
						.stream()
						.anyMatch(x ->
								"test6".equals(x.getName()))
		);

		assertEquals(
				1,
				whileBody.getSuccessions().size()
		);

		Succession succession =
				whileBody.getSuccessions().get(0);

		assertEquals(
				2,
				succession.getActionList().size()
		);

		Action first =
				succession.getActionList()
						.get(0)
						.getReferent();

		Action second =
				succession.getActionList()
						.get(1)
						.getReferent();

		assertEquals(
				"test5",
				first.getName()
		);

		assertEquals(
				"test6",
				second.getName()
		);

		assertFalse(
				result.get(Assignment.class)
						.isEmpty()
		);

		for (Assignment assignment :
				result.get(Assignment.class)) {

			assertNotNull(
					assignment.getTarget()
			);

			assertNotNull(
					assignment.getTarget()
							.getReferent()
			);

			assertNotNull(
					assignment.getValue()
			);
		}
	}

	@Test
	public void testAvgDerivedAttributeExpression() {

		TwinAttribute avgTemp =
				named(
						TwinAttribute.class,
						"avgTemp"
				);

		assertTrue(
				avgTemp.getTwinExpressions().isPresent()
		);

		Expression expression =
				avgTemp.getTwinExpressions().get();

		assertTrue(
				expression instanceof Calculation
		);

		Calculation avgCall =
				(Calculation) expression;

		assertEquals(
				1,
				avgCall.getArguments().size()
		);

		Expression argument =
				avgCall.getArguments().get(0);

		assertTrue(
				argument instanceof FeatureReference
		);

		FeatureReference chain =
				(FeatureReference) argument;

		assertEquals(
				"result",
				chain.getTarget()
						.getReferent()
						.getName()
		);
	}

	private void assertAssignmentTo(
			Assignment assignment,
			String targetName
	) {

		assertNotNull(
				assignment.getTarget()
		);

		assertNotNull(
				assignment.getTarget()
						.getReferent()
		);

		assertEquals(
				targetName,
				assignment.getTarget()
						.getReferent()
						.getName()
		);

		assertNotNull(
				assignment.getValue()
		);
	}

	private void assertCalculationGuard(
			List<Expression> guards
	) {

		assertEquals(
				1,
				guards.size()
		);

		assertTrue(
				guards.get(0)
						instanceof Calculation
		);

		Calculation calculation =
				(Calculation) guards.get(0);

		assertFalse(
				calculation.getArguments().isEmpty()
		);
	}
}