package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleCheckAssignToConst extends AbstarctTest {

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
				                out attribute maxCharge : TwinReal :>> measurements = MULT_real(nominalVoltage, 2);
				            }
				        }
				        part descriptiveBattery :>> descriptiveModel {
				            action soc :> derivedAttributes {
				                in attribute v : TwinReal;
				                assign physicalBattery.constPort.maxCharge := v;
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
	public void assignmentToConstShouldThrow() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> super.testTopLevelDefinition());
		assertTrue(exception.getMessage().contains("Assignment target 'maxCharge' has roles '[CONST]'"), exception.getMessage());
	}
}
