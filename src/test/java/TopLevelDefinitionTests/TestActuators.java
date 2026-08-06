package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Actuators;
import org.example.Mapping.Interfaces.Twin;
import org.junit.Assert;
import org.junit.Test;

public class TestActuators extends AbstarctTest {
	@Test
	public void testGeneralActuator() {
		assertAmount(Actuators.class, 1);

		result.get(Actuators.class).forEach(actuator -> assertParent(actuator, Twin.class, "Battery"));
	}

	@Test
	public void testP12ActuatorInterface() {
		Actuators p12 = named(Actuators.class, "p12");

		Assert.assertEquals("p12", p12.getName());
		Assert.assertNotNull(p12.getId());

		Assert.assertEquals(1, p12.getAttributes().size());
	}
}
