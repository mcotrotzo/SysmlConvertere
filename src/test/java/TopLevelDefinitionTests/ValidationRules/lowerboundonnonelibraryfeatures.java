package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;

public class lowerboundonnonelibraryfeatures extends AbstarctTest {

	@Override
	public Optional<String> getContent() {
		return Optional.of("""
				package PositionThings {
				    private import TwinLibrary::*;

				    attribute def Position :> TwinCustomType {
				        attribute x[1] : TwinInteger :> fields;
				        attribute y[1] : TwinInteger :> fields;
				        attribute z[1] : TwinInteger :> fields;
				    }
				}

				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;

				    part def Battery :> Twin {
				        part physicalBattery :>> physicalTwin {
				            port p11 :> sensors {
				                c1 :>>communicationProtocol:MQTT_Protocol{
				                    attribute :>>broker = "localhost";
				                    attribute :>>topic = "battery/measurements";
				                }
				                attribute :>>deviceKey = "actuatorKey";
				                attribute pos[3] : Position :> measurements;
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
	public void lowerBoundTestShouldNotThrow() {
		assertAll(() -> super.testTopLevelDefinition());

	}

}