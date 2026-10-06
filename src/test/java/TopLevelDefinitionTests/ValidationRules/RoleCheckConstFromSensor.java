package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleCheckConstFromSensor extends AbstarctTest {

	@Override
	public Optional<String> getContent() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;

				    part def Battery :> Twin {
				        part physicalBattery :>> physicalTwin {
				            port p11 :> sensors {
				                c1 :>> communicationProtocol : MQTT_Protocol {
				                    attribute :>> broker = "localhost";
				                    attribute :>> topic = "battery/measurements";
				                }
				                attribute :>> deviceKey = "sensorKey";
				                out attribute voltage : TwinReal :>> measurements;
				            }
				            out port constPort :>> constPort {
				                c1 :>> communicationProtocol : MQTT_Protocol {
				                    attribute :>> broker = "localhost";
				                    attribute :>> topic = "battery/const";
				                }
				                attribute :>> deviceKey = "constKey";
				                out attribute nominalVoltage : TwinReal :>> measurements = 48;
				                out attribute maxCharge : TwinReal :>> measurements = p11.voltage;
				            }
				        }
				        part descriptiveBattery :>> descriptiveModel {
				            action soc :> derivedAttributes {
				                out attribute soc : TwinReal;
				            }
				        }
				    }
				}
				""");
	}

	@Override
	public void testTopLevelDefinition() throws IOException, IllegalArgumentException {

	}

	@Test
	public void constFromSensorShouldThrow() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> super.testTopLevelDefinition());
		assertTrue(exception.getMessage().contains("CONST expressions may only reference CONST attributes"), exception.getMessage());
	}
}
