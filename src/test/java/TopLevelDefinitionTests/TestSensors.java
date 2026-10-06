package TopLevelDefinitionTests;

import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Port.SensorUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSensors extends AbstarctTest {

	@Test
	public void TestGeneralSensor() {
		assertAmount(SensorUsage.class, 4);
		List<SensorUsage> sensors = originals(SensorUsage.class);

		var librarySensor = sensors.stream().filter(x -> x.getParent().get().getName().equals("PhysicalTwin")).collect(java.util.stream.Collectors.toList());
		assertEquals(0, librarySensor.size());

		sensors.stream().filter(x -> !x.getParent().get().getName().equals("PhysicalTwin")).forEach(sensor -> this.assertParent(sensor, PhysicalTwinUsage.class, "physicalBattery"));

	}

	@Test
	public void testP11SensorInterface() {
		SensorUsage p11 = named(SensorUsage.class, "p11");

		assertEquals("p11", p11.getName());
		assertNotNull(p11.getId());
		assertTrue(p11.getParent().isPresent());

		assertTrue(p11.getProtocol().isPresent());

		assertEquals(8, p11.getMeasurements().size());
	}

	@Test
	public void testP13SensorInterface() {
		SensorUsage p13 = named(SensorUsage.class, "p13");
		SensorUsage p11 = named(SensorUsage.class, "p11");

		assertEquals("p13", p13.getName());
		assertNotNull(p13.getId());
		assertTrue(p13.getParent().isPresent());
		assertSame(
			p13.getProtocol().orElseThrow().getSysmlElement(),
			p11.getProtocol().orElseThrow().getSysmlElement()
		);

		assertEquals(p13.getMeasurements().size(), p11.getMeasurements().size());

	}

	@Test
	public void testP13InheritsP11Attributes() {
		SensorUsage p13 = named(SensorUsage.class, "p13");
		SensorUsage p11 = named(SensorUsage.class, "p11");

		List<TwinAttributeUsage<?, ?>> p11Attributes = p11.getMeasurements();
		List<TwinAttributeUsage<?, ?>> p13Attributes = p13.getMeasurements();

		assertEquals(
			p11Attributes.stream().map(c -> c.getSysmlElement()).toList(),
			p13Attributes.stream().map(c -> c.getSysmlElement()).toList()
		);

		assertTrue(p11Attributes.stream().noneMatch(c -> c.isInherited()));
		assertTrue(p13Attributes.stream().allMatch(c -> c.isInherited()));
	}
}
