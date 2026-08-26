package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.DataBase.RelationalDatabase;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.example.Mapping.Interfaces.TwinPort.ConstPort;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.example.Mapping.Interfaces.TwinStateMachine.TwinStateMachine;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.junit.jupiter.api.Test;
import org.omg.sysml.lang.sysml.impl.FunctionImpl;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTopLevel extends AbstarctTest {

	@Test
	public void testBatteryTwinInterface() {

		assertAmount(Twin.class, Definition.class, 2);
		Twin<Definition> battery = named(Twin.class, Definition.class, "Battery");

		assertEquals("Battery", battery.getName());
		assertNotNull(battery.getId());
		assertTrue(battery.getParent().isPresent());

		var physical = battery.getPhysicalTwin().orElseThrow();
		var shadow = battery.getShadow().orElseThrow();
		var descriptive = battery.getDescriptiveModel().orElseThrow();
		var predictive = battery.getPredictiveModel().orElseThrow();
		var prescriptive = battery.getPrescriptiveModel().orElseThrow();
		var queryFlows = battery.getQueryFlows();
		var descriptiveToPredictiveFlows = battery.getDescriptiveToPredictiveFlows();
		var descriptiveToPrescriptiveFlows = battery.getDescriptiveToPrescriptiveFlows();
		var predictiveToPrescriptiveFlows = battery.getPredictiveToPrescriptiveFlows();
		var prescriptiveToPhysicalFlows = battery.getPrescriptiveToPhysicalFlows();

		assertEquals(4, physical.getSensors().size());
		assertEquals(1, result.get(Sensors.class, Definition.class).size());
		assertEquals(1, physical.getActuators().size());
		assertEquals(1, physical.getControlUnits().size());
		assertEquals(9, physical.getConstPort().get().getAttributes().size());

		assertEquals(3, descriptive.getDerivedAttributes().size());
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

		Set<Sensors<Usage>> ports = result.get(Sensors.class, Usage.class);

		var p11 = ports.stream().filter(x -> x.getName().equals("p11")).findFirst().orElseThrow();

		var p13 = ports.stream().filter(x -> x.getName().equals("p13")).findFirst().orElseThrow();

		var p14 = ports.stream().filter(x -> x.getName().equals("p14")).findFirst().orElseThrow();

		var p15 = ports.stream().filter(x -> x.getName().equals("p15")).findFirst().orElseThrow();

		var p11Mult = result.getMultiplicity(p11);
		assertEquals(30, p11Mult.getLowerBound());
		assertEquals(30, p11Mult.getUpperBound());

		var p13Mult = result.getMultiplicity(p13);
		assertEquals(23, p13Mult.getLowerBound());
		assertEquals(23, p13Mult.getUpperBound());

		var p14Mult = result.getMultiplicity(p14);
		assertEquals(2, p14Mult.getLowerBound());
		assertEquals(2, p14Mult.getUpperBound());

		var p15Mult = result.getMultiplicity(p15);
		assertEquals(1, p15Mult.getLowerBound());
		assertEquals(1, p15Mult.getUpperBound());

		var p11Children = result.getSpecializationChildren(p11);

		var p13Children = result.getSpecializationChildren(p13);

		var p14Children = result.getSpecializationChildren(p14);

		var p15Children = result.getSpecializationChildren(p15);

		System.out.println("p11 children: " + p11Children.stream().map(Model::getName).toList());

		System.out.println("p13 children: " + p13Children.stream().map(Model::getName).toList());

		System.out.println("p14 children: " + p14Children.stream().map(Model::getName).toList());

		System.out.println("p15 children: " + p15Children.stream().map(Model::getName).toList());
	}


	@Test
	public void testLibraryDefinitionAmounts() {

		assertAmount(Sensors.class, Definition.class, 1);
		assertAmount(Actuators.class, Definition.class, 1);
		assertAmount(ConstPort.class, Definition.class, 1);


		assertAmount(TwinStateMachine.class, Definition.class, 5);
		assertAmount(TwinStateMachine.class, Usage.class, 14);


		assertAmount(Database.class, Definition.class, 2);
		assertAmount(RelationalDatabase.class, Definition.class, 1);


		assertAmount(QueryFlow.class, Definition.class, 1);
		assertAmount(Flow.class, Definition.class, 10);

		Set<Strategy<Definition>> strategies = result.get(Strategy.class, Definition.class);
		Set<Strategy<Usage>> usages = result.get(Strategy.class, Usage.class);

		strategies.forEach(x -> System.out.println("Strategy: " + x.getName() + " id: " + x.getId()));
		usages.forEach(x -> System.out.println("Strategy usage: " + x.getName() + " id: " + x.getId()));
		assertAmount(Strategy.class, Definition.class, 3);
		assertAmount(Strategy.class,Usage.class, 7);

		assertAmount(PhysicalTwin.class, Definition.class, 1);
		assertAmount(DescriptiveModel.class, Definition.class, 1);
		assertAmount(PredictiveModel.class, Definition.class, 1);
		assertAmount(PrescriptiveModel.class, Definition.class, 1);
		assertAmount(Shadow.class, Definition.class, 1);
	}
}