package TopLevelDefinitionTests.ValidationRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoweBoundLibraryFeatures extends AbstarctTest {

	@Override
	public Optional<String> getContent() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				part physicalBattery :>> physicalTwin{
				        port p11 :> sensors {
				        c1:>communicationProtocol:MQTT_Protocol {
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
	public void testLowerBoundNotFullFilled() {
		// MapperService.map() now reports rule violations as IllegalArgumentException (was MappingException)
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> super.testTopLevelDefinition());
		assertTrue(exception.getMessage().contains("does not fully concretize required feature "));

	}

}
