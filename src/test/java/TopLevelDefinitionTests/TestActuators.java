package TopLevelDefinitionTests;

import org.example.Mapping.Model.Port.ActuatorUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestActuators extends AbstarctTest {
	@Test
	public void testGeneralActuator() {
		// was 2: the library usage 'actuators' in PhysicalTwin is no longer mapped (library elements do not fill their slots)
		assertAmount(ActuatorUsage.class, 1);

		List<ActuatorUsage> actuators = originals(ActuatorUsage.class);

		actuators.stream().filter(x -> !x.getParent().get().getName().equals("PhysicalTwin")).forEach(actuator -> assertParent(actuator, PhysicalTwinUsage.class, "physicalBattery"));
	}

	@Test
	public void testP12ActuatorInterface() {
		ActuatorUsage p12 = named(ActuatorUsage.class, "p12");

		assertEquals("p12", p12.getName());
		assertNotNull(p12.getId());

		assertEquals(1, p12.getCommands().size());
	}
}
