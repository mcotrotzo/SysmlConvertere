package TopLevelDefinitionTests;

import org.example.Mapping.NewVersion.MappingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

public class TestQueryGrouped extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
				        port p11 :> sensors {
				        c1:>>communicationProtocol:MQTT_Protocol {
							attribute x : TwinBoolean :> measurements = DIV_real(10, 2);
				                                 }
				            attribute pos[3] : Position :> measurements;
				
				
				        }
				
				        part positionHistory :> groupedQueryHistory {
				            :>> twinAttribute : Position default p11.pos;
				            :>> groupBy default "x";
				            :>> result : PositionQueryResult[0..*];
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

		String testModelDirectory = createModelDirectoryOrGetPath(getTestModel());

		String userLibraryDirectory = createUserLibraryDirectoryOrGetPath(getUserLibrary());
	}

	@Test
	public void queryResultHasCorrectType() {
		MappingException exception = Assert.assertThrows(MappingException.class, () -> super.testTopLevelDefinition());


		Assert.assertTrue(exception.getMessage().contains("does not match QueryResult field type 'TwinRealMapped'."));
	}
}
