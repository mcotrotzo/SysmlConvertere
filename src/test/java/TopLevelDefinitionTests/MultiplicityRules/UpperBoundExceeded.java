package TopLevelDefinitionTests.MultiplicityRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.example.Mapping.NewVersion.MappingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpperBoundExceeded  extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
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
				""");
	}

	@Override
	public Optional<String> getUserLibrary() {
		return Optional.of("""
				package PositionThings {
				    private import UserLibrary::*;
				
				    attribute def Position :> TwinCustomType {
				        attribute x[1] : TwinInteger :> fields;
				        attribute y[1] : TwinInteger :> fields;
				        attribute z[1] : TwinInteger :> fields;
				    }
				
				    attribute def PositionQueryResult :> QueryResult {
				        :>> result : TwinReal[0..*];
				    }
				}
				""");
	}

	@Override
	public void testTopLevelDefinition() throws IOException, MappingException {

	}

	@Test
	public void upperBoundShouldThrow() {
		MappingException exception = assertThrows(MappingException.class, () -> super.testTopLevelDefinition());
		System.out.println(exception.getMessage());
		assertTrue(exception.getMessage().contains("2 > 1"));

	}

}
