package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.MQTTProtocol;
import org.example.Mapping.Interfaces.Sensors;
import org.example.Mapping.Interfaces.Twin;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class TestSensors extends AbstarctTest {

	@Test
	public void TestGeneralSensor() {
		assertAmount(Sensors.class, 2);
		Set<Sensors> sensors = result.get(Sensors.class);
		sensors.forEach(sensor -> this.assertParent(sensor, Twin.class, "Battery"));

	}

	@Test
	public void testP11SensorInterface() {
		Sensors p11 = named(Sensors.class, "p11");

		Assert.assertEquals("p11", p11.getName());
		Assert.assertNotNull(p11.getId());
		Assert.assertTrue(p11.getParent().isPresent());

		Assert.assertTrue(p11.getProtocol().isEmpty());

		Assert.assertEquals(7, p11.getAttributes().size());
	}

	@Test
	public void testP13SensorInterface() {
		Sensors p13 = named(Sensors.class, "p13");
		Sensors p11 = named(Sensors.class, "p11");

		Assert.assertEquals("p13", p13.getName());
		Assert.assertNotNull(p13.getId());
		Assert.assertTrue(p13.getParent().isPresent());
		Assert.assertEquals(p13.getProtocol(), p11.getProtocol());

		Assert.assertEquals(p13.getAttributes().size(), p11.getAttributes().size());

	}

	@Test
	public void testP13InheritsP11Attributes() {
		Sensors p13 = named(Sensors.class, "p13");
		Sensors p11 = named(Sensors.class, "p11");

		Assert.assertEquals(p11.getAttributes(), p13.getAttributes());
	}
}
