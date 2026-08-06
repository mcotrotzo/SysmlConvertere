package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Twin;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;


public class TestTopLevel extends AbstarctTest {


	@Test
	public void testBatteryTwinInterface() {

		assertAmount(Twin.class, 4);
		Twin battery = named(Twin.class, "Battery");

		Assert.assertEquals("Battery", battery.getName());
		Assert.assertNotNull(battery.getId());
		Assert.assertEquals(Optional.empty(), battery.getParent());

		Assert.assertEquals(2, battery.getSensors().size());
		Assert.assertEquals(1, battery.getActuators().size());
		Assert.assertEquals(1, battery.getControlUnits().size());
		Assert.assertEquals(8, battery.getConstAttributes().size());
		Assert.assertEquals(2, battery.getDerivedAttributes().size());
		Assert.assertEquals(1, battery.getQueriesHistory().size());
		Assert.assertEquals(1, battery.getGroupQueriesHistory().size());
		Assert.assertEquals(1, battery.getDescriptiveStateMachines().size());
		Assert.assertEquals(1, battery.getDescriptiveStrategies().size());
		Assert.assertEquals(1, battery.getPredictiveStrategies().size());
		Assert.assertEquals(1, battery.getPrescriptiveStrategies().size());
		Assert.assertEquals(1, battery.getDatabases().size());
	}


}
