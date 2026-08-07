package TopLevelDefinitionTests.MultiplicityRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.example.Mapping.NewVersion.MappingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LowerBoundLibraryFeaturesFromUserLibraryNotThrows extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
				        port p11 :> sensors:P11;
				    }
				}
				""");
	}

	@Override
	public Optional<String> getUserLibrary() {
		return Optional.of("""
				package PositionThings {
				    private import UserLibrary::*;
					
					port def P11:>Sensor{
					:>> communicationProtocol:MQTT_Protocol{
						:>>broker;
						:>>topic;
					}
					}
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
	public void lowerBoundFullfiled() {
		assertAll(()->super.testTopLevelDefinition());

	}

}

