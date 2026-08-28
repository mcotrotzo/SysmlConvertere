package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.DataBase.RelationalDatabase;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.ExternalStrategy;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestFullModelPresence extends AbstarctTest {

	@Test
	public void testBatteryTwinIsPresent() {
		assertAmount(Twin.class, Definition.class, 2);

		Twin<Definition> battery = named(Twin.class, Definition.class, "Battery");
		assertNotNull(battery.getId());

		assertTrue(battery.getPhysicalTwin().isPresent());
		assertTrue(battery.getShadow().isPresent());
		assertTrue(battery.getDescriptiveModel().isPresent());
		assertTrue(battery.getPredictiveModel().isPresent());
		assertTrue(battery.getPrescriptiveModel().isPresent());
	}

	@Test
	public void testPhysicalBatteryPresent() {
		PhysicalTwin<Usage> physicalBattery = named(PhysicalTwin.class, Usage.class, "physicalBattery");
		assertNotNull(physicalBattery.getId());

		assertEquals(4, physicalBattery.getSensors().getCompartment().size());
		assertEquals(1, physicalBattery.getActuators().getCompartment().size());
		assertEquals(1, physicalBattery.getControlUnits().getCompartment().size());
		assertEquals(1, physicalBattery.getConstPort().getCompartment().size());
		assertEquals(3, physicalBattery.getPhysicalFlows().getCompartment().size());
	}

	@Test
	public void testSensorP11AndItsAttributesArePresent() {
		Sensors<Usage> p11 = named(Sensors.class, Usage.class, "p11");
		assertParent(p11, PhysicalTwin.class, Usage.class, "physicalBattery");

		assertTrue(p11.getProtocol().isPresent());

		List<String> attributeNames = p11.getAttributes().getCompartment().stream()
				.map(c -> c.getElement().getName())
				.toList();

		assertEquals(8, attributeNames.size());

		for (String expected : List.of(
				"temp", "temp2", "voltage", "current",
				"plug", "pos", "pos2", "pos3"
		)) {
			assertTrue(
					attributeNames.contains(expected),
					"Missing attribute in p11: " + expected
			);
		}

		CustomType<Usage> pos = named(CustomType.class, Usage.class, "pos");
		assertEquals(3, pos.getFields().getCompartment().size());
	}

	@Test
	public void testSensorInheritanceChainIsPresent() {
		Sensors<Usage> p13 = named(Sensors.class, Usage.class, "p13");
		Sensors<Usage> p14 = named(Sensors.class, Usage.class, "p14");
		Sensors<Usage> p15 = named(Sensors.class, Usage.class, "p15");

		assertParent(p13, PhysicalTwin.class, Usage.class, "physicalBattery");
		assertParent(p14, PhysicalTwin.class, Usage.class, "physicalBattery");
		assertParent(p15, PhysicalTwin.class, Usage.class, "physicalBattery");

		assertEquals(8, p13.getAttributes().getCompartment().size());
		assertEquals(8, p14.getAttributes().getCompartment().size());
		assertEquals(8, p15.getAttributes().getCompartment().size());
	}

	@Test
	public void testConstPortAndItsAttributesArePresent() {
		Set<ConstPort<Usage>> constPorts =
				result.get(ConstPort.class, Usage.class);

		assertFalse(constPorts.isEmpty());

		ConstPort<Usage> constPort = constPorts.stream()
				.filter(x ->
						x.getParent().isPresent()
								&& "physicalBattery".equals(
								x.getParent().get().getName()
						)
				)
				.findFirst()
				.orElseThrow(() ->
						new AssertionError(
								"constPort with parent 'physicalBattery' not found"
						)
				);

		assertParent(
				constPort,
				PhysicalTwin.class,
				Usage.class,
				"physicalBattery"
		);

		assertTrue(constPort.getProtocol().isPresent());

		List<String> attributeNames =
				constPort.getAttributes().getCompartment().stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertEquals(9, attributeNames.size());

		for (String expected : List.of(
				"collectionTest",
				"baseFunctionTest",
				"constructorTest",
				"constructorTestBoolean",
				"customCalculationTest",
				"maxCharge",
				"nominalVoltage",
				"defaultNetDemand",
				"defaultNetSupply"
		)) {
			assertTrue(
					attributeNames.contains(expected),
					"Missing attribute in constPort: " + expected
			);
		}

		CustomType<Usage> customCalculationTest =
				named(
						CustomType.class,
						Usage.class,
						"customCalculationTest"
				);

		assertEquals(
				3,
				customCalculationTest.getFields().getCompartment().size()
		);
	}

	@Test
	public void testActuatorP12AndItsAttributesArePresent() {
		Actuators<Usage> p12 =
				named(Actuators.class, Usage.class, "p12");

		assertParent(
				p12,
				PhysicalTwin.class,
				Usage.class,
				"physicalBattery"
		);

		assertTrue(p12.getProtocol().isPresent());

		assertEquals(
				1,
				p12.getAttributes().getCompartment().size()
		);

		assertEquals(
				"charge",
				p12.getAttributes()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);
	}

	@Test
	public void testControlUnitCm1StructureIsPresent() {
		TwinStateMachine<Usage> cm1 =
				named(TwinStateMachine.class, Usage.class, "cm1");

		assertParent(
				cm1,
				PhysicalTwin.class,
				Usage.class,
				"physicalBattery"
		);

		assertEquals(3, cm1.getInputs().getCompartment().size());
		assertEquals(1, cm1.getOutputs().getCompartment().size());
		assertEquals(2, cm1.getStates().getCompartment().size());
		assertEquals(3, cm1.getTransitions().size());

		TwinStateMachine<Usage> charging =
				cm1.getStates().getCompartment().stream()
						.map(c -> c.getElement())
						.filter(s -> "charging".equals(s.getName()))
						.findFirst()
						.orElseThrow(() ->
								new AssertionError(
										"State 'charging' not found"
								)
						);

		assertEquals(
				1,
				charging.getStates().getCompartment().size()
		);

		assertEquals(
				"test34",
				charging.getStates()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);

		assertTrue(
				cm1.getStates().getCompartment().stream()
						.anyMatch(c ->
								"idle".equals(c.getElement().getName())
						)
		);
	}

	@Test
	public void testPhysicalFlowsArePresent() {
		PhysicalTwin<Usage> physicalBattery =
				named(
						PhysicalTwin.class,
						Usage.class,
						"physicalBattery"
				);

		List<String> targets =
				physicalBattery.getPhysicalFlows()
						.getCompartment()
						.stream()
						.map(c ->
								c.getElement()
										.getTarget()
										.getReferent()
										.getName()
						)
						.toList();

		assertEquals(3, targets.size());

		assertEquals(
				3,
				targets.stream().filter("temp"::equals).count()
						+ targets.stream().filter("plug"::equals).count()
						+ targets.stream().filter("maxCharge"::equals).count()
		);
	}

	@Test
	public void testShadowBatteryAndDatabaseArePresent() {
		Shadow<Usage> shadowBattery =
				named(Shadow.class, Usage.class, "shadowBattery");

		assertNotNull(shadowBattery.getId());

		assertEquals(
				1,
				shadowBattery.getDatabases().getCompartment().size()
		);

		Database<Usage> database =
				shadowBattery.getDatabases()
						.getCompartment()
						.getFirst()
						.getElement();

		assertInstanceOf(RelationalDatabase.class, database);

		assertEquals(
				1,
				result.get(RelationalDatabase.class, Usage.class).size()
		);
	}

	@Test
	public void testAllQueryFlowsArePresent() {
		Twin<Definition> battery =
				named(Twin.class, Definition.class, "Battery");

		List<? extends QueryFlow<Usage>> queryFlows =
				battery.getQueryFlows().getCompartment().stream()
						.map(c -> c.getElement())
						.toList();

		assertEquals(6, queryFlows.size());

		List<String> targetNames =
				queryFlows.stream()
						.map(f -> f.getTarget().getReferent().getName())
						.toList();

		for (String expected : List.of(
				"voltage",
				"nominalVoltage",
				"current",
				"temps",
				"maxCharge"
		)) {
			assertTrue(
					targetNames.contains(expected),
					"Missing queryFlow target: " + expected
			);
		}

		assertEquals(
				2,
				targetNames.stream()
						.filter("maxCharge"::equals)
						.count()
		);
	}

	@Test
	public void testDescriptiveBatteryStructureIsPresent() {
		DescriptiveModel<Usage> descriptiveBattery =
				named(
						DescriptiveModel.class,
						Usage.class,
						"descriptiveBattery"
				);

		assertNotNull(descriptiveBattery.getId());

		assertEquals(
				3,
				descriptiveBattery.getDerivedAttributes()
						.getCompartment()
						.size()
		);

		assertEquals(
				1,
				descriptiveBattery.getDescriptiveStateMachines()
						.getCompartment()
						.size()
		);

		assertEquals(
				1,
				descriptiveBattery.getDescriptiveStrategies()
						.getCompartment()
						.size()
		);

		assertEquals(
				3,
				descriptiveBattery.getDescriptiveFlows()
						.getCompartment()
						.size()
		);
	}

	@Test
	public void testSocActionIsPresent() {
		Action<Usage> soc =
				named(Action.class, Usage.class, "soc");

		assertParent(
				soc,
				DescriptiveModel.class,
				Usage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testTemp30ActionIsPresent() {
		Action<Usage> temp30 =
				named(Action.class, Usage.class, "temp30");

		assertParent(
				temp30,
				DescriptiveModel.class,
				Usage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testAvgTempActionIsPresent() {
		Action<Usage> avgTemp =
				named(Action.class, Usage.class, "avgTemp");

		assertParent(
				avgTemp,
				DescriptiveModel.class,
				Usage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testDescriptiveStateMachineTest12IsPresent() {
		TwinStateMachine<Usage> test12 =
				named(
						TwinStateMachine.class,
						Usage.class,
						"test12"
				);

		assertParent(
				test12,
				DescriptiveModel.class,
				Usage.class,
				"descriptiveBattery"
		);

		assertEquals(
				2,
				test12.getStates().getCompartment().size()
		);

		List<String> stateNames =
				test12.getStates().getCompartment().stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertTrue(stateNames.contains("sa"));
		assertTrue(stateNames.contains("sd"));
	}

	@Test
	public void testLlmRequestStrategyIsPresent() {
		Strategy<Usage> llmRequest =
				named(
						Strategy.class,
						Usage.class,
						"LLM_Request"
				);

		assertParent(
				llmRequest,
				DescriptiveModel.class,
				Usage.class,
				"descriptiveBattery"
		);

		assertInstanceOf(ExternalStrategy.class, llmRequest);

		ExternalStrategy<Usage> external =
				(ExternalStrategy<Usage>) llmRequest;

		assertEquals(
				"battery/LLM_Request",
				contentPathOf(external)
		);

		assertEquals(
				CustomStrategyType.CONTAINER,
				strategyTypeOf(external)
		);

		assertEquals(
				3,
				llmRequest.getInputs().getCompartment().size()
		);

		assertEquals(
				1,
				llmRequest.getOutputs().getCompartment().size()
		);
	}

	@Test
	public void testDescriptiveFlowsArePresent() {
		DescriptiveModel<Usage> descriptiveBattery =
				named(
						DescriptiveModel.class,
						Usage.class,
						"descriptiveBattery"
				);

		List<String> targets =
				descriptiveBattery.getDescriptiveFlows()
						.getCompartment()
						.stream()
						.map(c ->
								c.getElement()
										.getTarget()
										.getReferent()
										.getName()
						)
						.toList();

		assertEquals(3, targets.size());
		assertTrue(targets.contains("temps"));
		assertTrue(targets.contains("avgTemperature"));
		assertTrue(targets.contains("soc"));
	}

	@Test
	public void testDescriptiveToPredictiveFlowsArePresent() {
		Twin<Definition> battery =
				named(Twin.class, Definition.class, "Battery");

		List<? extends Flow<Usage>> flows =
				battery.getDescriptiveToPredictiveFlows()
						.getCompartment()
						.stream()
						.map(c -> c.getElement())
						.toList();

		assertEquals(2, flows.size());

		List<String> targets =
				flows.stream()
						.map(f ->
								f.getTarget().getReferent().getName()
						)
						.toList();

		assertTrue(targets.contains("avgTemperature"));
		assertTrue(targets.contains("current"));
	}

	@Test
	public void testPredictiveBatteryStructureIsPresent() {
		PredictiveModel<Usage> predictiveBattery =
				named(
						PredictiveModel.class,
						Usage.class,
						"predictiveBattery"
				);

		assertNotNull(predictiveBattery.getId());

		assertEquals(
				1,
				predictiveBattery.getPredictiveStrategies()
						.getCompartment()
						.size()
		);
	}

	@Test
	public void testConsForecastStrategyIsPresent() {
		Strategy<Usage> consForecast =
				named(
						Strategy.class,
						Usage.class,
						"consForecast"
				);

		assertParent(
				consForecast,
				PredictiveModel.class,
				Usage.class,
				"predictiveBattery"
		);

		assertInstanceOf(ExternalStrategy.class, consForecast);

		ExternalStrategy<Usage> external =
				(ExternalStrategy<Usage>) consForecast;

		assertEquals(
				"battery/consForecast",
				contentPathOf(external)
		);

		assertEquals(
				CustomStrategyType.LAMBDA,
				strategyTypeOf(external)
		);

		assertEquals(
				2,
				consForecast.getInputs().getCompartment().size()
		);

		assertEquals(
				1,
				consForecast.getOutputs().getCompartment().size()
		);
	}

	@Test
	public void testPredictiveToPrescriptiveFlowsArePresent() {
		Twin<Definition> battery =
				named(Twin.class, Definition.class, "Battery");

		List<? extends Flow<Usage>> flows =
				battery.getPredictiveToPrescriptiveFlows()
						.getCompartment()
						.stream()
						.map(c -> c.getElement())
						.toList();

		assertEquals(2, flows.size());

		assertTrue(
				flows.stream().allMatch(f ->
						"predictedCurrent".equals(
								f.getTarget().getReferent().getName()
						)
				)
		);
	}

	@Test
	public void testPrescriptiveToPhysicalFlowsArePresent() {
		Twin<Definition> battery =
				named(Twin.class, Definition.class, "Battery");

		List<? extends Flow<Usage>> flows =
				battery.getPrescriptiveToPhysicalFlows()
						.getCompartment()
						.stream()
						.map(c -> c.getElement())
						.toList();

		assertEquals(2, flows.size());

		assertTrue(
				flows.stream().allMatch(f ->
						"charge".equals(
								f.getTarget().getReferent().getName()
						)
				)
		);
	}

	@Test
	public void testPrescriptiveBatteryStructureIsPresent() {
		PrescriptiveModel<Usage> prescriptiveBattery =
				named(
						PrescriptiveModel.class,
						Usage.class,
						"prescriptiveBattery"
				);

		assertNotNull(prescriptiveBattery.getId());

		assertEquals(
				2,
				prescriptiveBattery.getPrescriptiveStrategies()
						.getCompartment()
						.size()
		);
	}

	@Test
	public void testChargeStrategyExternalIsPresent() {
		Strategy<Usage> chargeStrategyExternal =
				named(
						Strategy.class,
						Usage.class,
						"chargeStrategyExternal"
				);

		assertParent(
				chargeStrategyExternal,
				PrescriptiveModel.class,
				Usage.class,
				"prescriptiveBattery"
		);

		assertInstanceOf(
				ExternalStrategy.class,
				chargeStrategyExternal
		);

		ExternalStrategy<Usage> external =
				(ExternalStrategy<Usage>) chargeStrategyExternal;

		assertEquals(
				"battery/chargeStrategy",
				contentPathOf(external)
		);

		assertEquals(
				CustomStrategyType.CONTAINER,
				strategyTypeOf(external)
		);

		assertEquals(
				2,
				chargeStrategyExternal.getInputs()
						.getCompartment()
						.size()
		);

		assertEquals(
				1,
				chargeStrategyExternal.getOutputs()
						.getCompartment()
						.size()
		);
	}

	@Test
	public void testChargeStrategyInternalIsPresent() {
		Strategy<Usage> chargeStrategyInternal =
				named(
						Strategy.class,
						Usage.class,
						"chargeStrategyInternal"
				);

		assertParent(
				chargeStrategyInternal,
				PrescriptiveModel.class,
				Usage.class,
				"prescriptiveBattery"
		);

		assertEquals(
				3,
				chargeStrategyInternal.getInputs()
						.getCompartment()
						.size()
		);

		assertEquals(
				1,
				chargeStrategyInternal.getOutputs()
						.getCompartment()
						.size()
		);

		List<String> inputNames =
				chargeStrategyInternal.getInputs()
						.getCompartment()
						.stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertTrue(inputNames.contains("predictedCurrent"));
		assertTrue(inputNames.contains("maxCharge"));
		assertTrue(inputNames.contains("posTest12"));

		TwinAttribute<Usage> test12 =
				named(
						TwinAttribute.class,
						Usage.class,
						"test12"
				);

		assertTrue(test12.getExpression().isPresent());

		CustomType<Usage> posTest12 =
				named(
						CustomType.class,
						Usage.class,
						"posTest12"
				);

		assertEquals(
				3,
				posTest12.getFields().getCompartment().size()
		);
	}

	@Test
	public void testPositionCustomTypeDefinitionIsPresent() {
		CustomType<Definition> position =
				named(
						CustomType.class,
						Definition.class,
						"Position"
				);

		assertNotNull(position.getId());

		List<String> fieldNames =
				position.getFields().getCompartment().stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertEquals(3, fieldNames.size());
		assertTrue(fieldNames.contains("x"));
		assertTrue(fieldNames.contains("y"));
		assertTrue(fieldNames.contains("z"));
	}

	@Test
	public void testPosition2CustomTypeDefinitionIsPresent() {
		CustomType<Definition> position2 =
				named(
						CustomType.class,
						Definition.class,
						"Position2"
				);

		assertNotNull(position2.getId());

		assertEquals(
				1,
				position2.getFields().getCompartment().size()
		);

		assertEquals(
				"x",
				position2.getFields()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);
	}

	@Test
	public void testAddPositionCalculationDefinitionIsPresent() {
		CustomCalculation addPosition =
				named(
						CustomCalculation.class,
						Definition.class,
						"AddPosition"
				);

		assertNotNull(addPosition.getId());

		assertEquals(
				2,
				addPosition.getInputs().getCompartment().size()
		);

		List<String> inputNames =
				addPosition.getInputs().getCompartment().stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertTrue(inputNames.contains("pos_a"));
		assertTrue(inputNames.contains("pos_b"));

		assertEquals(
				1,
				addPosition.getOutputs().getCompartment().size()
		);

		assertEquals(
				"pos_c",
				addPosition.getOutputs()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);
	}

	@Test
	public void testAvgCalculationDefinitionIsPresent() {
		CustomCalculation avg =
				named(
						CustomCalculation.class,
						Definition.class,
						"Avg"
				);

		assertNotNull(avg.getId());

		assertEquals(
				1,
				avg.getInputs().getCompartment().size()
		);

		assertEquals(
				"reals",
				avg.getInputs()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);

		assertEquals(
				1,
				avg.getOutputs().getCompartment().size()
		);

		assertEquals(
				"avg",
				avg.getOutputs()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);

		assertTrue(
				avg.getActions().stream()
						.anyMatch(a -> "test".equals(a.getName()))
		);
	}

	@Test
	public void testAvg2CalculationDefinitionIsPresent() {
		CustomCalculation avg2 =
				named(
						CustomCalculation.class,
						Definition.class,
						"Avg2"
				);

		assertNotNull(avg2.getId());

		List<String> inputNames =
				avg2.getInputs().getCompartment().stream()
						.map(c -> c.getElement().getName())
						.toList();

		assertTrue(inputNames.contains("x"));
		assertTrue(inputNames.contains("y"));
	}

	@Test
	public void testAllCalculationDefinitionIsPresent() {
		CustomCalculation all =
				named(
						CustomCalculation.class,
						Definition.class,
						"All"
				);

		assertNotNull(all.getId());

		assertEquals(
				1,
				all.getInputs().getCompartment().size()
		);

		assertEquals(
				"bools",
				all.getInputs()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);

		assertEquals(
				1,
				all.getOutputs().getCompartment().size()
		);

		assertEquals(
				"alltrue",
				all.getOutputs()
						.getCompartment()
						.getFirst()
						.getElement()
						.getName()
		);
	}

	@Test
	public void testAllTwinLevelFlowCompartmentsSumUpConsistently() {
		Twin<Definition> battery =
				named(Twin.class, Definition.class, "Battery");

		int sum =
				battery.getQueryFlows().getCompartment().size()
						+ battery.getDescriptiveToPredictiveFlows()
						.getCompartment()
						.size()
						+ battery.getDescriptiveToPrescriptiveFlows()
						.getCompartment()
						.size()
						+ battery.getPredictiveToPrescriptiveFlows()
						.getCompartment()
						.size()
						+ battery.getPrescriptiveToPhysicalFlows()
						.getCompartment()
						.size();

		assertEquals(12, sum);
	}

	private String contentPathOf(
			ExternalStrategy<Usage> strategy
	) {
		return strategy.getContentPath()
				.getElement()
				.getExpression()
				.filter(
						org.example.Mapping.Interfaces.TwinExpression
								.StringLiteral.class::isInstance
				)
				.map(
						org.example.Mapping.Interfaces.TwinExpression
								.StringLiteral.class::cast
				)
				.map(
						org.example.Mapping.Interfaces.TwinExpression
								.StringLiteral::getLiteralValue
				)
				.orElseThrow(() ->
						new AssertionError(
								"contentPath has no StringLiteral value"
						)
				);
	}

	private CustomStrategyType strategyTypeOf(
			ExternalStrategy<Usage> strategy
	) {
		return strategy.getStrategyType()
				.getElement()
				.getTwinEnum()
				.orElseThrow(() ->
						new AssertionError(
								"strategyType has no enum value"
						)
				);
	}
}