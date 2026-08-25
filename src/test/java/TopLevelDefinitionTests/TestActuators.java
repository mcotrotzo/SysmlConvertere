package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.TwinPort.Actuators;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestActuators extends AbstarctTest {
	@Test
	public void testGeneralActuator() {
		assertAmount(Actuators.class, Usage.class, 1);

		Set<Actuators<Usage>> actuators = result.get(Actuators.class, Usage.class);

		actuators.forEach(actuator -> assertParent(actuator, PhysicalTwin.class, Usage.class, "physicalBattery"));
	}

	@Test
	public void testP12ActuatorInterface() {
		Actuators<Usage> p12 = named(Actuators.class, Usage.class, "p12");

		assertEquals("p12", p12.getName());
		assertNotNull(p12.getId());

		assertEquals(1, p12.getAttributes().size());
	}
}
