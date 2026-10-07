package TopLevelDefinitionTests;

import org.example.ElemWithMult;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Database.DatabaseDefinition;
import org.example.Mapping.Model.Database.RelationalDatabaseDefinition;
import org.example.Mapping.Model.Flow.TwinFlowDefinition;
import org.example.Mapping.Model.Port.ActuatorDefinition;
import org.example.Mapping.Model.Port.ConstPortDefinition;
import org.example.Mapping.Model.Port.SensorDefinition;
import org.example.Mapping.Model.Port.SensorUsage;
import org.example.Mapping.Model.StateMachine.TwinStateDefinition;
import org.example.Mapping.Model.StateMachine.TwinStateMachineDefinition;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.StateMachine.TwinStateUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyDefinition;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinDefiniton;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowDefinition;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowUsage;
import org.example.Mapping.Model.Twin.TwinDefinition;
import org.example.Mapping.Model.Type.Usage;
import org.example.Util.Utils;
import org.junit.jupiter.api.Test;
import org.omg.sysml.util.TypeUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTopLevel extends AbstarctTest {

	@Test
	public void testBatteryTwinInterface() {

		assertAmount(TwinDefinition.class, 3);
		TwinDefinition battery = named(TwinDefinition.class, "Battery");

		assertEquals("Battery", battery.getName());
		assertNotNull(battery.getId());
		assertTrue(battery.getParent().isEmpty());

		PhysicalTwinUsage physical = battery.getPhysicalTwin().orElseThrow();
		ShadowUsage shadow = battery.getShadow().orElseThrow();
		DescriptiveModelUsage descriptive = battery.getDescriptiveModel().orElseThrow();
		PredictiveModelUsage predictive = battery.getPredictiveModel().orElseThrow();
		PrescriptiveModelUsage prescriptive = battery.getPrescriptiveModel().orElseThrow();
		var queryFlows = battery.getQueryFlows();
		var descriptiveToPredictiveFlows = battery.getDescriptiveToPredictiveFlows();
		var descriptiveToPrescriptiveFlows = battery.getDescriptiveToPrescriptiveFlows();
		var predictiveToPrescriptiveFlows = battery.getPredictiveToPrescriptiveFlows();
		var prescriptiveToPhysicalFlows = battery.getPrescriptiveToPhysicalFlows();

		assertEquals(4, physical.getSensors().size());
		assertEquals(1, result.getByType(SensorDefinition.class).size());
		assertEquals(1, physical.getActuators().size());
		assertEquals(1, physical.getControlUnits().size());
		assertEquals(9, physical.getConstPorts().getFirst().getMeasurements().size());

		assertEquals(4, descriptive.getDerivedAttributes().size());
		assertEquals(1, descriptive.getDescriptiveStateMachines().size());
		assertEquals(1, descriptive.getDescriptiveStrategies().size());

		assertEquals(1, predictive.getPredictiveStrategies().size());

		assertEquals(2, prescriptive.getPrescriptiveStrategies().size());

		assertEquals(1, shadow.getDatabases().size());

		assertEquals(6, queryFlows.size());
		assertEquals(2, descriptiveToPredictiveFlows.size());
		assertEquals(0, descriptiveToPrescriptiveFlows.size());
		assertEquals(2, predictiveToPrescriptiveFlows.size());
		assertEquals(2, prescriptiveToPhysicalFlows.size());

	}

	@Test
	public void testSpecializationChildrenAndMultiplicity() {

		List<SensorUsage> ports = originals(SensorUsage.class);

		var p11 = ports.stream().filter(x -> x.getName().equals("p11")).findFirst().orElseThrow();

		var p13 = ports.stream().filter(x -> x.getName().equals("p13")).findFirst().orElseThrow();

		var p14 = ports.stream().filter(x -> x.getName().equals("p14")).findFirst().orElseThrow();

		var p15 = ports.stream().filter(x -> x.getName().equals("p15")).findFirst().orElseThrow();


		assertEquals(30, p11.getMultiplicity().getLowerBound());
		assertEquals(30, p11.getMultiplicity().getUpperBound());
		assertEquals(23, p13.getMultiplicity().getLowerBound());
		assertEquals(23, p13.getMultiplicity().getUpperBound());
		assertEquals(2, p14.getMultiplicity().getLowerBound());
		assertEquals(2, p14.getMultiplicity().getUpperBound());
		assertEquals(1, p15.getMultiplicity().getLowerBound());
		assertEquals(1, p15.getMultiplicity().getUpperBound());

		var p11Children = p11.getSpecializations();

		var p13Children = p13.getSpecializations();

		var p14Children = p14.getSpecializations();

		var p15Children = p15.getSpecializations();

		System.out.println("p11 children: " + p11Children.stream().map(AbstractModel::getName).toList());

		System.out.println("p13 children: " + p13Children.stream().map(AbstractModel::getName).toList());

		System.out.println("p14 children: " + p14Children.stream().map(AbstractModel::getName).toList());

		System.out.println("p15 children: " + p15Children.stream().map(AbstractModel::getName).toList());
	}



	@Test
	public void testLibraryDefinitionAmounts() {

		assertAmount(SensorDefinition.class, 1);
		assertAmount(ActuatorDefinition.class, 1);
		assertAmount(ConstPortDefinition.class, 1);


		assertAmount(TwinStateMachineDefinition.class, 3);
		assertAmount(TwinStateDefinition.class, 3);
		assertAmount(TwinStateMachineUsage.class, 2);
		assertAmount(TwinStateUsage.class, 5);


		assertAmount(DatabaseDefinition.class, 3);
		assertAmount(RelationalDatabaseDefinition.class, 1);


		assertEquals(1, originals(TwinFlowDefinition.class).stream().filter(x -> "QueryFlow".equals(x.getName())).count());
		assertAmount(TwinFlowDefinition.class, 11);

		var strategies = originals(TwinStrategyDefinition.class);
		var usages = originals(TwinStrategyUsage.class);

		assertAmount(TwinStrategyDefinition.class, 3);
		assertAmount(TwinStrategyUsage.class, 4);

		assertAmount(PhysicalTwinDefiniton.class, 1);
		assertAmount(DescriptiveModelDefinition.class, 1);
		assertAmount(PredictiveModelDefinition.class, 1);
		assertAmount(PrescriptiveModelDefinition.class, 1);
		assertAmount(ShadowDefinition.class, 1);
	}
}
