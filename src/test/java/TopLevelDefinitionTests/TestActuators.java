package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Actuators;
import org.example.Mapping.Interfaces.PhysicalTwin;
import org.example.Mapping.Interfaces.Twin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestActuators extends AbstarctTest {
	@Test
	public void testGeneralActuator() {
		assertAmount(Actuators.class, 1);

		result.get(Actuators.class).forEach(actuator -> assertParent(actuator, PhysicalTwin.class, "physicalBattery"));
	}

	@Test
	public void testP12ActuatorInterface() {
		Actuators p12 = named(Actuators.class, "p12");

		assertEquals("p12", p12.getName());
		assertNotNull(p12.getId());

		assertEquals(1, p12.getAttributes().size());
	}
}
