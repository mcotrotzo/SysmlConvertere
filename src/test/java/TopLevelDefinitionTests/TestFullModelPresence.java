package TopLevelDefinitionTests;

import org.example.Mapping.CustomStrategyType;
import org.example.Mapping.EnumFederationLink;
import org.example.Mapping.Model.Action.TwinActionUsage;
import org.example.Mapping.Model.Attribute.CustomTypeDefinition;
import org.example.Mapping.Model.Attribute.CustomTypeUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Database.DatabaseUsage;
import org.example.Mapping.Model.Database.RelationalDatabaseUsage;
import org.example.Mapping.Model.Expression.TwinLiteralStringUsage;
import org.example.Mapping.Model.Federation.FederationTwinDefinition;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Flow.QueryFlowUsage;
import org.example.Mapping.Model.Flow.TwinFlowUsage;
import org.example.Mapping.Model.Function.CustomCalculationDefinition;
import org.example.Mapping.Model.Port.ActuatorUsage;
import org.example.Mapping.Model.Port.ConstPortUsage;
import org.example.Mapping.Model.Port.SensorUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.Strategy.ExternalStrategyUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowUsage;
import org.example.Mapping.Model.Twin.TwinDefinition;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFullModelPresence extends AbstarctTest {

	@Test
	public void testBatteryTwinIsPresent() {
		assertAmount(TwinDefinition.class, 3);

		TwinDefinition battery = named(TwinDefinition.class, "Battery");
		assertNotNull(battery.getId());

		assertTrue(battery.getPhysicalTwin().isPresent());
		assertTrue(battery.getShadow().isPresent());
		assertTrue(battery.getDescriptiveModel().isPresent());
		assertTrue(battery.getPredictiveModel().isPresent());
		assertTrue(battery.getPrescriptiveModel().isPresent());
	}

	@Test
	public void testPhysicalBatteryPresent() {
		PhysicalTwinUsage physicalBattery = named(PhysicalTwinUsage.class, "physicalBattery");
		assertNotNull(physicalBattery.getId());

		assertEquals(4, physicalBattery.getSensors().size());
		assertEquals(1, physicalBattery.getActuators().size());
		assertEquals(1, physicalBattery.getControlUnits().size());
		assertEquals(1, physicalBattery.getConstPorts().size());
		assertEquals(3, physicalBattery.getPhysicalFlows().size());
	}

	@Test
	public void testSensorP11AndItsAttributesArePresent() {
		SensorUsage p11 = named(SensorUsage.class, "p11");
		assertParent(p11, PhysicalTwinUsage.class, "physicalBattery");

		assertTrue(p11.getProtocol().isPresent());
		assertTrue(assertInstanceOf(TwinLiteralStringUsage.class, p11.getDeviceKey().getExpression().get()).getValue().equals("battery_sensor"));

		List<String> attributeNames = p11.getMeasurements().stream()
				.map(c -> c.getName())
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

		CustomTypeUsage pos = named(CustomTypeUsage.class, "pos");
		assertEquals(3, pos.getFields().size());
	}
	@Test
	public void testDescriptiveFlowReferencesPointToCorrectCompartments() {
		DescriptiveModelUsage descriptiveBattery =
				named(
						DescriptiveModelUsage.class,
						"descriptiveBattery"
				);

		List<? extends TwinFlowUsage> flows =
				descriptiveBattery.getDescriptiveFlows()
						.stream()
						.toList();

		assertEquals(3, flows.size());

		assertTrue(
				flows.stream().allMatch(flow -> {
					var source = flow.getSource();
					var target = flow.getTarget();

					String sourceName = source.getName();
					String sourceParent =
							source.getParent().orElseThrow().getName();

					String targetName = target.getName();
					String targetParent =
							target.getParent().orElseThrow().getName();

					if (sourceName.equals("res")) {
						return sourceParent.equals("temp30")
								&& targetName.equals("temps")
								&& targetParent.equals("avgTemp");
					}

					if (sourceName.equals("avgTemp")) {
						return sourceParent.equals("avgTemp")
								&& targetName.equals("avgTemperature")
								&& targetParent.equals("LLM_Request");
					}

					if (sourceName.equals("soc")) {
						return sourceParent.equals("soc")
								&& targetName.equals("soc")
								&& targetParent.equals("LLM_Request");
					}

					return false;
				})
		);
	}

	@Test
	public void testPhysicalFlowReferencesPointToCorrectCompartments() {
		PhysicalTwinUsage physicalBattery =
				named(PhysicalTwinUsage.class, "physicalBattery");

		List<? extends TwinFlowUsage> flows =
				physicalBattery.getPhysicalFlows()
						.stream()
						.toList();

		assertEquals(3, flows.size());

		assertTrue(
				flows.stream().allMatch(flow -> {
					var source = flow.getSource();
					var target = flow.getTarget();

					String sourceName = source.getName();
					String sourceParent =
							source.getParent().orElseThrow().getName();

					String targetName = target.getName();
					String targetParent =
							target.getParent().orElseThrow().getName();

					if (sourceName.equals("temp")) {
						return sourceParent.equals("p13")
								&& targetName.equals("temp")
								&& targetParent.equals("cm1")
								&& source.isInherited();
					}

					if (sourceName.equals("plug")) {
						return sourceParent.equals("p11")
								&& targetName.equals("plug")
								&& targetParent.equals("cm1");
					}

					if (sourceName.equals("maxCharge")) {
						return sourceParent.equals("constPort")
								&& targetName.equals("maxCharge")
								&& targetParent.equals("cm1");
					}

					return false;
				})
		);
	}

	@Test
	public void testQueryFlowReferencesPointToCorrectCompartments() {
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		List<QueryFlowUsage> flows =
				battery.getQueryFlows()
						.stream()
						.toList();

		assertEquals(6, flows.size());

		assertTrue(
				flows.stream().allMatch(flow -> {
					var source = flow.getSource();
					var target = flow.getTarget();

					String sourceName = source.getName();
					String sourceParent =
							source.getParent().orElseThrow().getName();

					String targetName = target.getName();
					String targetParent =
							target.getParent().orElseThrow().getName();

					if ("testflow".equals(flow.getName())) {
						return sourceName.equals("voltage")
								&& sourceParent.equals("p11")
								&& targetName.equals("voltage")
								&& targetParent.equals("soc2")
								&& target.isInherited();
					}

					if (sourceName.equals("nominalVoltage")) {
						return sourceParent.equals("constPort")
								&& targetName.equals("nominalVoltage")
								&& targetParent.equals("soc");
					}

					if (sourceName.equals("current")) {
						return sourceParent.equals("p11")
								&& targetName.equals("current")
								&& targetParent.equals("LLM_Request");
					}

					if (sourceName.equals("temp")) {
						return sourceParent.equals("p11")
								&& targetName.equals("temps")
								&& targetParent.equals("temp30");
					}

					if (sourceName.equals("maxCharge")) {
						return sourceParent.equals("constPort")
								&& targetName.equals("maxCharge")
								&& (
								targetParent.equals("chargeStrategyExternal")
										|| targetParent.equals("chargeStrategyInternal")
						);
					}

					return false;
				})
		);
	}

	@Test
	public void testSensorInheritanceChainIsPresent() {
		SensorUsage p11 = named(SensorUsage.class, "p11");
		SensorUsage p13 = named(SensorUsage.class, "p13");
		SensorUsage p14 = named(SensorUsage.class, "p14");
		SensorUsage p15 = named(SensorUsage.class, "p15");

		assertParent(p13, PhysicalTwinUsage.class, "physicalBattery");
		assertParent(p14, PhysicalTwinUsage.class, "physicalBattery");
		assertParent(p15, PhysicalTwinUsage.class, "physicalBattery");

		assertEquals(8, p13.getMeasurements().size());
		assertEquals(8, p14.getMeasurements().size());
		assertEquals(8, p15.getMeasurements().size());
		assertEquals(
				p11.getProtocol().get().getSysmlElement(),
				p13.getProtocol().get().getSysmlElement()
		);	}



	@Test
	public void testConstPortAndItsAttributesArePresent() {
		List<ConstPortUsage> constPorts =
				originals(ConstPortUsage.class);

		assertFalse(constPorts.isEmpty());

		ConstPortUsage constPort = constPorts.stream()
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
				PhysicalTwinUsage.class,
				"physicalBattery"
		);

		assertTrue(constPort.getProtocol().isPresent());

		List<String> attributeNames =
				constPort.getMeasurements().stream()
						.map(c -> c.getName())
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

		CustomTypeUsage customCalculationTest =
				named(
						CustomTypeUsage.class,
						"customCalculationTest"
				);

		assertEquals(
				3,
				customCalculationTest.getFields().size()
		);
	}

	@Test
	public void testActuatorP12AndItsAttributesArePresent() {
		ActuatorUsage p12 =
				named(ActuatorUsage.class, "p12");

		assertParent(
				p12,
				PhysicalTwinUsage.class,
				"physicalBattery"
		);

		assertTrue(p12.getProtocol().isPresent());

		assertEquals(
				1,
				p12.getCommands().size()
		);

		assertEquals(
				"charge",
				p12.getCommands()
						.getFirst()
						.getName()
		);
	}

	@Test
	public void testControlUnitCm1StructureIsPresent() {
		TwinStateMachineUsage cm1 =
				named(TwinStateMachineUsage.class, "cm1");

		assertParent(
				cm1,
				PhysicalTwinUsage.class,
				"physicalBattery"
		);

		assertEquals(3, cm1.getInputs().size());
		assertEquals(1, cm1.getOutputs().size());
		assertEquals(2, cm1.getStates().size());
		assertEquals(3, cm1.getTransitions().size());

		TwinStateMachineUsage charging =
				cm1.getStates().stream()
						.filter(s -> "charging".equals(s.getName()))
						.findFirst()
						.orElseThrow(() ->
								new AssertionError(
										"State 'charging' not found"
								)
						);

		assertEquals(
				1,
				charging.getStates().size()
		);

		assertEquals(
				"test34",
				charging.getStates()
						.getFirst()
						.getName()
		);

		assertTrue(
				cm1.getStates().stream()
						.anyMatch(c ->
								"idle".equals(c.getName())
						)
		);
	}

	@Test
	public void testPhysicalFlowsArePresent() {
		PhysicalTwinUsage physicalBattery =
				named(
						PhysicalTwinUsage.class,
						"physicalBattery"
				);

		List<String> targets =
				physicalBattery.getPhysicalFlows()
						
						.stream()
						.map(c ->
								c
										.getTarget()
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
		ShadowUsage shadowBattery =
				named(ShadowUsage.class, "shadowBattery");

		assertNotNull(shadowBattery.getId());

		assertEquals(
				1,
				shadowBattery.getDatabases().size()
		);

		DatabaseUsage<?, ?> database =
				shadowBattery.getDatabases()
						.getFirst();

		assertInstanceOf(RelationalDatabaseUsage.class, database);

		assertEquals(
				1,
				originals(RelationalDatabaseUsage.class).size()
		);
	}

	@Test
	public void testAllQueryFlowsArePresent() {
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		List<QueryFlowUsage> queryFlows =
				battery.getQueryFlows().stream()
						.toList();

		assertEquals(6, queryFlows.size());

		List<String> targetNames =
				queryFlows.stream()
						.map(f -> f.getTarget().getName())
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
		DescriptiveModelUsage descriptiveBattery =
				named(
						DescriptiveModelUsage.class,
						"descriptiveBattery"
				);

		assertNotNull(descriptiveBattery.getId());

		assertEquals(
				4,
				descriptiveBattery.getDerivedAttributes()
						
						.size()
		);

		assertEquals(
				1,
				descriptiveBattery.getDescriptiveStateMachines()
						
						.size()
		);

		assertEquals(
				1,
				descriptiveBattery.getDescriptiveStrategies()
						
						.size()
		);

		assertEquals(
				3,
				descriptiveBattery.getDescriptiveFlows()
						
						.size()
		);
	}

	@Test
	public void testSocActionIsPresent() {
		TwinActionUsage<?, ?, ?> soc =
				named(TwinActionUsage.class, "soc");

		assertParent(
				soc,
				DescriptiveModelUsage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testTemp30ActionIsPresent() {
		TwinActionUsage<?, ?, ?> temp30 =
				named(TwinActionUsage.class, "temp30");

		assertParent(
				temp30,
				DescriptiveModelUsage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testAvgTempActionIsPresent() {
		TwinActionUsage<?, ?, ?> avgTemp =
				named(TwinActionUsage.class, "avgTemp");

		assertParent(
				avgTemp,
				DescriptiveModelUsage.class,
				"descriptiveBattery"
		);
	}

	@Test
	public void testDescriptiveStateMachineTest12IsPresent() {
		TwinStateMachineUsage test12 =
				named(
						TwinStateMachineUsage.class,
						"test12"
				);

		assertParent(
				test12,
				DescriptiveModelUsage.class,
				"descriptiveBattery"
		);

		assertEquals(
				2,
				test12.getStates().size()
		);

		List<String> stateNames =
				test12.getStates().stream()
						.map(c -> c.getName())
						.toList();

		assertTrue(stateNames.contains("sa"));
		assertTrue(stateNames.contains("sd"));
	}

	@Test
	public void testLlmRequestStrategyIsPresent() {
		TwinStrategyUsage<?, ?> llmRequest =
				named(
						TwinStrategyUsage.class,
						"LLM_Request"
				);

		assertParent(
				llmRequest,
				DescriptiveModelUsage.class,
				"descriptiveBattery"
		);

		ExternalStrategyUsage external = assertInstanceOf(ExternalStrategyUsage.class, llmRequest);

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
				llmRequest.getInputs().size()
		);

		assertEquals(
				1,
				llmRequest.getOutputs().size()
		);
	}

	@Test
	public void testDescriptiveFlowsArePresent() {
		DescriptiveModelUsage descriptiveBattery =
				named(
						DescriptiveModelUsage.class,
						"descriptiveBattery"
				);

		List<String> targets =
				descriptiveBattery.getDescriptiveFlows()
						
						.stream()
						.map(c ->
								c
										.getTarget()
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
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		List<? extends TwinFlowUsage> flows =
				battery.getDescriptiveToPredictiveFlows()
						.stream()
						.toList();

		assertEquals(2, flows.size());

		List<String> targets =
				flows.stream()
						.map(f ->
								f.getTarget().getName()
						)
						.toList();

		assertTrue(targets.contains("avgTemperature"));
		assertTrue(targets.contains("current"));
	}

	@Test
	public void testPredictiveBatteryStructureIsPresent() {
		PredictiveModelUsage predictiveBattery =
				named(
						PredictiveModelUsage.class,
						"predictiveBattery"
				);

		assertNotNull(predictiveBattery.getId());

		assertEquals(
				1,
				predictiveBattery.getPredictiveStrategies()
						
						.size()
		);
	}

	@Test
	public void testConsForecastStrategyIsPresent() {
		TwinStrategyUsage<?, ?> consForecast =
				named(
						TwinStrategyUsage.class,
						"consForecast"
				);

		assertParent(
				consForecast,
				PredictiveModelUsage.class,
				"predictiveBattery"
		);

		ExternalStrategyUsage external = assertInstanceOf(ExternalStrategyUsage.class, consForecast);

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
				consForecast.getInputs().size()
		);

		assertEquals(
				1,
				consForecast.getOutputs().size()
		);
	}

	@Test
	public void testPredictiveToPrescriptiveFlowsArePresent() {
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		List<? extends TwinFlowUsage> flows =
				battery.getPredictiveToPrescriptiveFlows()
						.stream()
						.toList();

		assertEquals(2, flows.size());

		assertTrue(
				flows.stream().allMatch(f ->
						"predictedCurrent".equals(
								f.getTarget().getName()
						)
				)
		);
	}

	@Test
	public void testPrescriptiveToPhysicalFlowsArePresent() {
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		List<? extends TwinFlowUsage> flows =
				battery.getPrescriptiveToPhysicalFlows()
						.stream()
						.toList();

		assertEquals(2, flows.size());

		assertTrue(
				flows.stream().allMatch(f ->
						"charge".equals(
								f.getTarget().getName()
						)
				)
		);
	}

	@Test
	public void testPrescriptiveBatteryStructureIsPresent() {
		PrescriptiveModelUsage prescriptiveBattery =
				named(
						PrescriptiveModelUsage.class,
						"prescriptiveBattery"
				);

		assertNotNull(prescriptiveBattery.getId());

		assertEquals(
				2,
				prescriptiveBattery.getPrescriptiveStrategies()
						
						.size()
		);
	}

	@Test
	public void testChargeStrategyExternalIsPresent() {
		TwinStrategyUsage<?, ?> chargeStrategyExternal =
				named(
						TwinStrategyUsage.class,
						"chargeStrategyExternal"
				);

		assertParent(
				chargeStrategyExternal,
				PrescriptiveModelUsage.class,
				"prescriptiveBattery"
		);

		ExternalStrategyUsage external = assertInstanceOf(ExternalStrategyUsage.class, chargeStrategyExternal);

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
						
						.size()
		);

		assertEquals(
				1,
				chargeStrategyExternal.getOutputs()
						
						.size()
		);

	}

	@Test
	public void testChargeStrategyInternalIsPresent() {
		TwinStrategyUsage<?, ?> chargeStrategyInternal =
				named(
						TwinStrategyUsage.class,
						"chargeStrategyInternal"
				);

		assertParent(
				chargeStrategyInternal,
				PrescriptiveModelUsage.class,
				"prescriptiveBattery"
		);

		assertEquals(
				3,
				chargeStrategyInternal.getInputs()
						
						.size()
		);

		assertEquals(
				1,
				chargeStrategyInternal.getOutputs()
						
						.size()
		);

		List<String> inputNames =
				chargeStrategyInternal.getInputs()
						
						.stream()
						.map(c -> c.getName())
						.toList();

		assertTrue(inputNames.contains("predictedCurrent"));
		assertTrue(inputNames.contains("maxCharge"));
		assertTrue(inputNames.contains("posTest12"));

		TwinAttributeUsage<?, ?> test12 =
				named(
						TwinAttributeUsage.class,
						"test12"
				);

		assertTrue(test12.getExpression().isPresent());

		CustomTypeUsage posTest12 =
				named(
						CustomTypeUsage.class,
						"posTest12"
				);

		assertEquals(
				3,
				posTest12.getFields().size()
		);
	}

	@Test
	public void testPositionCustomTypeDefinitionIsPresent() {
		CustomTypeDefinition position =
				named(
						CustomTypeDefinition.class,
						"Position"
				);

		assertNotNull(position.getId());

		List<String> fieldNames =
				position.getFields().stream()
						.map(c -> c.getName())
						.toList();

		assertEquals(3, fieldNames.size());
		assertTrue(fieldNames.contains("x"));
		assertTrue(fieldNames.contains("y"));
		assertTrue(fieldNames.contains("z"));
	}

	@Test
	public void testPosition2CustomTypeDefinitionIsPresent() {
		CustomTypeDefinition position2 =
				named(
						CustomTypeDefinition.class,
						"Position2"
				);

		assertNotNull(position2.getId());

		assertEquals(
				1,
				position2.getFields().size()
		);

		assertEquals(
				"x",
				position2.getFields()
						.getFirst()
						.getName()
		);
	}

	@Test
	public void testAddPositionCalculationDefinitionIsPresent() {
		CustomCalculationDefinition addPosition =
				named(
						CustomCalculationDefinition.class,
						"AddPosition"
				);

		assertNotNull(addPosition.getId());

		assertEquals(
				2,
				addPosition.getInputs().size()
		);

		List<String> inputNames =
				addPosition.getInputs().stream()
						.map(c -> c.getName())
						.toList();

		assertTrue(inputNames.contains("pos_a"));
		assertTrue(inputNames.contains("pos_b"));

		assertEquals(
				1,
				addPosition.getOutputs().size()
		);

		assertEquals(
				"pos_c",
				addPosition.getOutputs()
						.getFirst()
						.getName()
		);
	}

	@Test
	public void testAvgCalculationDefinitionIsPresent() {
		CustomCalculationDefinition avg =
				named(
						CustomCalculationDefinition.class,
						"Avg"
				);

		assertNotNull(avg.getId());

		assertEquals(
				1,
				avg.getInputs().size()
		);

		assertEquals(
				"reals",
				avg.getInputs()
						.getFirst()
						.getName()
		);

		assertEquals(
				1,
				avg.getOutputs().size()
		);

		assertEquals(
				"avg",
				avg.getOutputs()
						.getFirst()
						.getName()
		);

		assertTrue(
				avg.getActions().stream()
						.anyMatch(a -> "test".equals(a.getName()))
		);
	}

	@Test
	public void testAvg2CalculationDefinitionIsPresent() {
		CustomCalculationDefinition avg2 =
				named(
						CustomCalculationDefinition.class,
						"Avg2"
				);

		assertNotNull(avg2.getId());

		List<String> inputNames =
				avg2.getInputs().stream()
						.map(c -> c.getName())
						.toList();

		assertTrue(inputNames.contains("x"));
		assertTrue(inputNames.contains("y"));

		assertFalse(
				inheritedOf(avg2, "x"),
				"'x' should not be inherited"
		);
		assertFalse(
				inheritedOf(avg2, "y"),
				"'y' should not be inherited"
		);

		assertTrue(
				avg2.getLocalAttributes().stream()
						.anyMatch(c -> "test2".equals(c.getName())),
				"'test2' local attribute should be present"
		);
		assertTrue(
				localInheritedOf(avg2, "test2"),
				"'test2' local attribute should be inherited"
		);

		assertTrue(
				avg2.getOutputs().stream()
						.anyMatch(c -> "avg".equals(c.getName())),
				"return 'avg' should be in outputs"
		);
	}
	@Test
	public void testFederatedBatteryIsPresent() {
		FederationTwinDefinition federatedBattery =
				named(FederationTwinDefinition.class, "FederatedBattery");

		assertNotNull(federatedBattery.getId());

		List<FederationFlowUsage> federationFlows =
				federatedBattery.getFederationFlows().stream()
						.toList();

		assertEquals(1, federationFlows.size());

		FederationFlowUsage flow = federationFlows.getFirst();


		assertEquals("test", flow.getSource().getName());
		assertEquals("soc", flow.getTarget().getName());


		assertTrue(flow.getLinkType().isPresent(), "federation flow should have a linkType");
		assertEquals(
				EnumFederationLink.PUSH,
				flow.getLinkType().get().getValue().orElseThrow(() ->
						new AssertionError("linkType has no enum value"))
		);


	}

	private boolean inheritedOf(CustomCalculationDefinition calc, String inputName) {
		return calc.getInputs().stream()
				.filter(c -> inputName.equals(c.getName()))
				.findFirst()
				.orElseThrow(() ->
						new AssertionError("Input '" + inputName + "' not found")
				)
				.isInherited();
	}

	private boolean localInheritedOf(CustomCalculationDefinition calc, String name) {
		return calc.getLocalAttributes().stream()
				.filter(c -> name.equals(c.getName()))
				.findFirst()
				.orElseThrow(() ->
						new AssertionError("Local attribute '" + name + "' not found"))
				.isInherited();
	}

	@Test
	public void testAllCalculationDefinitionIsPresent() {
		CustomCalculationDefinition all =
				named(
						CustomCalculationDefinition.class,
						"All"
				);

		assertNotNull(all.getId());

		assertEquals(
				1,
				all.getInputs().size()
		);

		assertEquals(
				"bools",
				all.getInputs()
						.getFirst()
						.getName()
		);

		assertEquals(
				1,
				all.getOutputs().size()
		);

		assertEquals(
				"alltrue",
				all.getOutputs()
						.getFirst()
						.getName()
		);
	}

	@Test
	public void testAllTwinLevelFlowCompartmentsSumUpConsistently() {
		TwinDefinition battery =
				named(TwinDefinition.class, "Battery");

		int sum =
				battery.getQueryFlows().size()
						+ battery.getDescriptiveToPredictiveFlows()
						
						.size()
						+ battery.getDescriptiveToPrescriptiveFlows()
						
						.size()
						+ battery.getPredictiveToPrescriptiveFlows()
						
						.size()
						+ battery.getPrescriptiveToPhysicalFlows()
						
						.size();

		assertEquals(12, sum);
	}

	private String contentPathOf(
			ExternalStrategyUsage strategy
	) {
		return strategy.getContentPath()
				.getExpression()
				.filter(TwinLiteralStringUsage.class::isInstance)
				.map(TwinLiteralStringUsage.class::cast)
				.map(TwinLiteralStringUsage::getValue)
				.orElseThrow(() ->
						new AssertionError(
								"contentPath has no StringLiteral value"
						)
				);
	}

	private CustomStrategyType strategyTypeOf(
			ExternalStrategyUsage strategy
	) {
		return strategy.getStrategyType()
				.getValue()
				.orElseThrow(() ->
						new AssertionError(
								"strategyType has no enum value"
						)
				);
	}
}