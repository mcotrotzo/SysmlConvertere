package TopLevelDefinitionTests.ValidationRules;


import Executor.SemanticException;
import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlowCheckTargetDirection extends AbstarctTest {

	@Override
	public Optional<String> getContent() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;

				    attribute def Position :> TwinCustomType {
				        attribute x[1] : TwinInteger :> fields;
				    }

				    attribute def Position2 :> TwinCustomType {
				        attribute y[1] : TwinInteger :> fields;
				    }

				    part def Battery :> Twin {
				        part physicalBattery :>> physicalTwin {
				            port p11 :> sensors {
				                c1 :>> communicationProtocol : MQTT_Protocol {
				                    attribute :>> broker = "localhost";
				                    attribute :>> topic = "battery/measurements";
				                }
				                attribute :>> deviceKey = "sensorKey";
				                out attribute voltage : TwinReal :>> measurements;
				                out attribute pos : Position :>> measurements;
				            }
				        }
				        part descriptiveBattery :>> descriptiveModel {
				            action soc :> derivedAttributes {
				                in attribute voltage : TwinReal;
				                in attribute pos : Position2;
				                out attribute soc : TwinReal;
				            }
				            action other :> derivedAttributes {
				                in attribute inp : TwinReal;
				            }
				        }
				        flow :>queryFlows from physicalBattery.p11.voltage to descriptiveBattery.soc.soc;
				    }
				}
				""");
	}

	@Override
	public void testTopLevelDefinition() throws IOException, IllegalArgumentException {

	}

	@Test
	public void outputAsTargetShouldThrow() {
		SemanticException exception = assertThrows(SemanticException.class, () -> super.testTopLevelDefinition());
		assertTrue(exception.getMessage().contains("target 'soc' must have direction in or INOUT, but got 'out'"), exception.getMessage());
	}
}
