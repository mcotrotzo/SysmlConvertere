package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RoleCheckValid extends AbstarctTest {

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
				                in attribute voltage : TwinReal;
				                in attribute nominalVoltage : TwinReal;
				                out attribute soc : TwinReal;
				                attribute tmp : TwinReal :> local_Attributes = MULT_real(voltage, 2);
				                assign soc := DIV_real(tmp, nominalVoltage);
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
	public void validRolesShouldNotThrow() {
		assertDoesNotThrow(() -> super.testTopLevelDefinition());
	}
}
