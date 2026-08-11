package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.Interfaces.Sensors;
import org.example.Mapping.Interfaces.Twin;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class TestTopLevel extends AbstarctTest {


	@Test
	public void testBatteryTwinInterface() {

		assertAmount(Twin.class, 1);
		Twin battery = named(Twin.class, "Battery");

		assertEquals("Battery", battery.getName());
		assertNotNull(battery.getId());
		assertEquals(Optional.empty(), battery.getParent());

		assertEquals(4, battery.getSensors().size());
		assertEquals(1, battery.getActuators().size());
		assertEquals(1, battery.getControlUnits().size());
		assertEquals(9, battery.getConstAttributes().size());
		assertEquals(2, battery.getDerivedAttributes().size());
		assertEquals(1, battery.getQueriesHistory().size());
		assertEquals(1, battery.getGroupQueriesHistory().size());
		assertEquals(1, battery.getDescriptiveStateMachines().size());
		assertEquals(1, battery.getDescriptiveStrategies().size());
		assertEquals(1, battery.getPredictiveStrategies().size());
		assertEquals(1, battery.getPrescriptiveStrategies().size());
		assertEquals(1, battery.getDatabases().size());
	}

	@Test
	public void testSpecializationChildrenAndMultiplicity() {

		var ports = result.get(Sensors.class);

		var p11 = ports.stream()
				.filter(x -> x.getName().equals("p11"))
				.findFirst()
				.orElseThrow();

		var p13 = ports.stream()
				.filter(x -> x.getName().equals("p13"))
				.findFirst()
				.orElseThrow();

		var p14 = ports.stream()
				.filter(x -> x.getName().equals("p14"))
				.findFirst()
				.orElseThrow();

		var p15 = ports.stream()
				.filter(x -> x.getName().equals("p15"))
				.findFirst()
				.orElseThrow();

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



		var p11Children =
				result.getSpecializationChildren(p11);

		var p13Children =
				result.getSpecializationChildren(p13);

		var p14Children =
				result.getSpecializationChildren(p14);

		var p15Children =
				result.getSpecializationChildren(p15);


		System.out.println(
				"p11 children: " +
						p11Children.stream().map(Model::getName).toList()
		);

		System.out.println(
				"p13 children: " +
						p13Children.stream().map(Model::getName).toList()
		);

		System.out.println(
				"p14 children: " +
						p14Children.stream().map(Model::getName).toList()
		);

		System.out.println(
				"p15 children: " +
						p15Children.stream().map(Model::getName).toList()
		);
	}


}
