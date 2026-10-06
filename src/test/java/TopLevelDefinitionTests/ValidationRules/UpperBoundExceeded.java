package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpperBoundExceeded extends AbstarctTest {

	@Override
	public Optional<String> getContent() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				part physicalBattery :>> physicalTwin {
				        port p11 :> sensors {
				       c1[1]:>communicationProtocol:MQTT_Protocol {
							:>>broker[1];
							:>>topic[1];
				        }
				       c2[1]:>communicationProtocol:MQTT_Protocol {
							:>>broker[1];
							:>>topic[1];
				        }
				        }
				
				    }
				    }
				}
				package PositionThings {
				    private import TwinLibrary::*;
				
				    attribute def Position :> TwinCustomType {
				        attribute x[1] : TwinInteger :> fields;
				        attribute y[1] : TwinInteger :> fields;
				        attribute z[1] : TwinInteger :> fields;
				    }
				
				}
				""");
	}

	@Override
	public void testTopLevelDefinition() throws IOException, IllegalArgumentException {

	}

	@Test
	public void upperBoundShouldThrow() {
		// MapperService.map() now reports rule violations as IllegalArgumentException (was MappingException)
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> super.testTopLevelDefinition());
		System.out.println(exception.getMessage());
		assertTrue(exception.getMessage().contains("2 > 1"));

	}

}
