package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.TwinPort.Sensors;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestSensors extends AbstarctTest {

	@Test
	public void TestGeneralSensor() {
		assertAmount(Sensors.class, Usage.class, 5);
		Set<Sensors<Usage>> sensors = result.get(Sensors.class, Usage.class);

		var librarySensor = sensors.stream().filter(x -> x.getParent().get().getName().equals("PhysicalTwin")).collect(java.util.stream.Collectors.toList());
		assertEquals(1, librarySensor.size());

		sensors.stream().filter(x -> !x.getParent().get().getName().equals("PhysicalTwin")).forEach(sensor -> this.assertParent(sensor, PhysicalTwin.class, Usage.class, "physicalBattery"));

	}

	@Test
	public void testP11SensorInterface() {
		Sensors<Usage> p11 = named(Sensors.class, Usage.class, "p11");

		assertEquals("p11", p11.getName());
		assertNotNull(p11.getId());
		assertTrue(p11.getParent().isPresent());

		assertTrue(p11.getProtocol().isPresent());

		assertEquals(8, p11.getAttributes().getCompartment().size());
	}

	@Test
	public void testP13SensorInterface() {
		Sensors<Usage> p13 = named(Sensors.class, Usage.class, "p13");
		Sensors<Usage> p11 = named(Sensors.class, Usage.class, "p11");

		assertEquals("p13", p13.getName());
		assertNotNull(p13.getId());
		assertTrue(p13.getParent().isPresent());
		assertEquals(
			p13.getProtocol().orElseThrow().getElement().getId(),
			p11.getProtocol().orElseThrow().getElement().getId()
		);

		assertEquals(p13.getAttributes().getCompartment().size(), p11.getAttributes().getCompartment().size());

	}

	@Test
	public void testP13InheritsP11Attributes() {
		Sensors<Usage> p13 = named(Sensors.class, Usage.class, "p13");
		Sensors<Usage> p11 = named(Sensors.class, Usage.class, "p11");

		var p11Attributes = p11.getAttributes().getCompartment();
		var p13Attributes = p13.getAttributes().getCompartment();

		assertEquals(
			p11Attributes.stream().map(c -> c.getElement().getId()).toList(),
			p13Attributes.stream().map(c -> c.getElement().getId()).toList()
		);

		assertTrue(p11Attributes.stream().noneMatch(c -> c.isInherited()));
		assertTrue(p13Attributes.stream().allMatch(c -> c.isInherited()));
	}
}
