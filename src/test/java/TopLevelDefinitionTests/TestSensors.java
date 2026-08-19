package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.MQTTProtocol;
import org.example.Mapping.Interfaces.PhysicalTwin;
import org.example.Mapping.Interfaces.Sensors;
import org.example.Mapping.Interfaces.Twin;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestSensors extends AbstarctTest {

	@Test
	public void TestGeneralSensor() {
		assertAmount(Sensors.class, 4);
		Set<Sensors> sensors = result.get(Sensors.class);
		sensors.forEach(sensor -> this.assertParent(sensor, PhysicalTwin.class, "physicalBattery"));

	}

	@Test
	public void testP11SensorInterface() {
		Sensors p11 = named(Sensors.class, "p11");

		assertEquals("p11", p11.getName());
		assertNotNull(p11.getId());
		assertTrue(p11.getParent().isPresent());

		assertTrue(p11.getProtocol().isEmpty());

		assertEquals(8, p11.getAttributes().size());
	}

	@Test
	public void testP13SensorInterface() {
		Sensors p13 = named(Sensors.class, "p13");
		Sensors p11 = named(Sensors.class, "p11");

		assertEquals("p13", p13.getName());
		assertNotNull(p13.getId());
		assertTrue(p13.getParent().isPresent());
		assertEquals(p13.getProtocol(), p11.getProtocol());

		assertEquals(p13.getAttributes().size(), p11.getAttributes().size());

	}

	@Test
	public void testP13InheritsP11Attributes() {
		Sensors p13 = named(Sensors.class, "p13");
		Sensors p11 = named(Sensors.class, "p11");

		assertEquals(p11.getAttributes(), p13.getAttributes());
	}
}
