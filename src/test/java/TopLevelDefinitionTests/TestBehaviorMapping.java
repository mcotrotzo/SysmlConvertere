package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestBehaviorMapping extends AbstarctTest {

	@Test
	public void testControlUnitComplete() {

		ControlUnit controlUnit =
				named(ControlUnit.class, "cm1");

		Assert.assertEquals("cm1", controlUnit.getName());
		Assert.assertNotNull(controlUnit.getId());

		assertParent(
				controlUnit,
				Twin.class,
				"Battery"
		);

		Assert.assertEquals(
				1,
				controlUnit.getLocalAttributes().size()
		);

		Assert.assertTrue(
				controlUnit.getLocalAttributes()
						.stream()
						.anyMatch(x ->
								"maxCharge".equals(x.getName()))
		);

		Assert.assertEquals(
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

		Assert.assertNotNull(idle);
		Assert.assertNotNull(charging);

		Assert.assertEquals(
				1,
				charging.getStates().size()
		);

		StateMachine test34 =
				charging.getStates().get(0);

		Assert.assertEquals(
				"test34",
				test34.getName()
		);

		Assert.assertTrue(
				charging.getEntryAction()
						instanceof Assignment
		);

		Assert.assertTrue(
				charging.getDoAction()
						instanceof Assignment
		);

		Assert.assertTrue(
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

		Assert.assertTrue(
				test34.getEntryAction()
						instanceof Assignment
		);

		Assert.assertTrue(
				test34.getDoAction()
						instanceof Assignment
		);

		Assert.assertTrue(
				test34.getExitAction()
						instanceof Assignment
		);

		Assignment test34Entry =
				(Assignment) test34.getEntryAction();

		Assert.assertEquals(
				"charge",
				test34Entry.getTarget()
						.getReferent()
						.getName()
		);

		Assert.assertTrue(
				test34Entry.getValue()
						instanceof FeatureChain
		);

		FeatureChain chain =
				(FeatureChain) test34Entry.getValue();

		Assert.assertEquals(
				"temp",
				chain.getTarget()
						.getReferent()
						.getName()
		);

		Assert.assertEquals(
				3,
				controlUnit.getTransitions().size()
		);

		for (Transition transition :
				controlUnit.getTransitions()) {

			Assert.assertNotNull(
					transition.getSource()
			);

			Assert.assertNotNull(
					transition.getTarget()
			);

			Assert.assertFalse(
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

		Assert.assertEquals(
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

			Assert.assertNotNull(source);
			Assert.assertNotNull(target);

			String sourceName =
					source.getName();

			String targetName =
					target.getName();

			if ("idle".equals(sourceName)
					&& "charging".equals(targetName)) {

				idleToCharging = true;

				Assert.assertEquals(
						1,
						transition.getGuard().size()
				);

				Expression guard =
						transition.getGuard().get(0);

				Assert.assertTrue(
						guard instanceof FeatureChain
								|| guard instanceof FeatureReference
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

		Assert.assertTrue(idleToCharging);
		Assert.assertTrue(idleToIdle);
		Assert.assertTrue(chargingToIdle);
	}

	@Test
	public void testDescriptiveStateMachineComplete() {

		DescriptiveStateMachine machine =
				named(
						DescriptiveStateMachine.class,
						"test12"
				);

		Assert.assertEquals(
				"test12",
				machine.getName()
		);

		assertParent(
				machine,
				Twin.class,
				"Battery"
		);

		Assert.assertEquals(
				2,
				machine.getStates().size()
		);

		Assert.assertTrue(
				machine.getStates()
						.stream()
						.anyMatch(x ->
								"sa".equals(x.getName()))
		);

		Assert.assertTrue(
				machine.getStates()
						.stream()
						.anyMatch(x ->
								"sd".equals(x.getName()))
		);

		Assert.assertEquals(
				1,
				machine.getTriggerConfiguration().size()
		);

		Assert.assertTrue(
				machine.getTriggerConfiguration()
						.get(0)
						instanceof TimeBasedConfiguration
		);

		TimeBasedConfiguration timeBasedConfiguration = (TimeBasedConfiguration) machine.getTriggerConfiguration().get(0);
		Assert.assertEquals(EnumTimeUnit.MINUTE, timeBasedConfiguration.getTriggerIntervalUnit());
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
				Twin.class,
				"Battery"
		);

		Assert.assertEquals(
				2,
				strategy.getInputs().size()
		);

		Assert.assertTrue(
				strategy.getInputs()
						.stream()
						.anyMatch(x ->
								"avgTemperature".equals(x.getName()))
		);

		Assert.assertTrue(
				strategy.getInputs()
						.stream()
						.anyMatch(x ->
								"current".equals(x.getName()))
		);

		Assert.assertEquals(
				1,
				strategy.getOutputs().size()
		);

		Assert.assertEquals(
				"predicted",
				strategy.getOutputs()
						.get(0)
						.getName()
		);

		Assert.assertEquals(
				1,
				strategy.getTriggerConfiguration().size()
		);

		Assert.assertTrue(
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
				Twin.class,
				"Battery"
		);

		Assert.assertEquals(
				1,
				strategy.getOutputs().size()
		);

		TwinAttribute chargeCmd =
				strategy.getOutputs().get(0);

		Assert.assertEquals(
				"chargeCmd",
				chargeCmd.getName()
		);

		Assert.assertTrue(
				chargeCmd.getTwinExpressions().isPresent()
		);

		Expression outputExpression =
				chargeCmd.getTwinExpressions().get();

		Assert.assertTrue(
				outputExpression instanceof FeatureChain
						|| outputExpression
						instanceof FeatureReference
		);

		Assert.assertEquals(
				1,
				strategy.getTriggerConfiguration().size()
		);

		Assert.assertTrue(
				strategy.getTriggerConfiguration()
						.get(0)
						instanceof EventBasedConfiguration
		);

	}

	@Test
	public void testAvgCustomCalculationActionsComplete() {

		CustomCalculation avg =
				named(
						CustomCalculation.class,
						"Avg"
				);

		Assert.assertEquals(
				1,
				avg.getInputs().size()
		);

		Assert.assertEquals(
				"reals",
				avg.getInputs()
						.get(0)
						.getName()
		);

		Assert.assertEquals(
				1,
				avg.getOutputs().size()
		);

		Assert.assertEquals(
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

		Assert.assertTrue(
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

		Assert.assertNotNull(
				forLoop.getLoopVariable()
		);

		Assert.assertEquals(
				"value",
				forLoop.getLoopVariable()
						.getName()
		);

		Assert.assertNotNull(
				forLoop.getCollection()
		);

		Assert.assertNotNull(
				forLoop.getBody()
		);

		Assert.assertTrue(
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

		Assert.assertNotNull(
				ifElse.getCondition()
		);

		Assert.assertNotNull(
				ifElse.getThenAction()
		);

		Assert.assertNotNull(
				ifElse.getElseAction()
		);

		Assert.assertTrue(
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

		Assert.assertNotNull(
				whileLoop.getCondition()
		);

		Assert.assertNotNull(
				whileLoop.getBody()
		);

		Assert.assertTrue(
				whileLoop.getBody()
						instanceof Block
		);

		Block whileBody =
				(Block) whileLoop.getBody();

		Assert.assertTrue(
				whileBody.getActions()
						.stream()
						.anyMatch(x ->
								"test5".equals(x.getName()))
		);

		Assert.assertTrue(
				whileBody.getActions()
						.stream()
						.anyMatch(x ->
								"test6".equals(x.getName()))
		);

		Assert.assertEquals(
				1,
				whileBody.getSuccessions().size()
		);

		Succession succession =
				whileBody.getSuccessions().get(0);

		Assert.assertEquals(
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

		Assert.assertEquals(
				"test5",
				first.getName()
		);

		Assert.assertEquals(
				"test6",
				second.getName()
		);

		Assert.assertFalse(
				result.get(Assignment.class)
						.isEmpty()
		);

		for (Assignment assignment :
				result.get(Assignment.class)) {

			Assert.assertNotNull(
					assignment.getTarget()
			);

			Assert.assertNotNull(
					assignment.getTarget()
							.getReferent()
			);

			Assert.assertNotNull(
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

		Assert.assertTrue(
				avgTemp.getTwinExpressions().isPresent()
		);

		Expression expression =
				avgTemp.getTwinExpressions().get();

		Assert.assertTrue(
				expression instanceof Calculation
		);

		Calculation avgCall =
				(Calculation) expression;

		Assert.assertEquals(
				1,
				avgCall.getArguments().size()
		);

		Expression argument =
				avgCall.getArguments().get(0);

		Assert.assertTrue(
				argument instanceof FeatureChain
		);

		FeatureChain chain =
				(FeatureChain) argument;

		Assert.assertEquals(
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

		Assert.assertNotNull(
				assignment.getTarget()
		);

		Assert.assertNotNull(
				assignment.getTarget()
						.getReferent()
		);

		Assert.assertEquals(
				targetName,
				assignment.getTarget()
						.getReferent()
						.getName()
		);

		Assert.assertNotNull(
				assignment.getValue()
		);
	}

	private void assertCalculationGuard(
			List<Expression> guards
	) {

		Assert.assertEquals(
				1,
				guards.size()
		);

		Assert.assertTrue(
				guards.get(0)
						instanceof Calculation
		);

		Calculation calculation =
				(Calculation) guards.get(0);

		Assert.assertFalse(
				calculation.getArguments().isEmpty()
		);
	}
}