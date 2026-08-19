package TopLevelDefinitionTests;

import org.example.Mapping.NewVersion.MappingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestQueryResultWithWrongMultiplicity extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
				part physicalBattery :>> physicalTwin {
				        port p11 :> sensors {
				         
				            attribute pos : Position :> measurements;
				        }
				        }
				part descriptiveBattery :>> descriptiveModel {
				        part positionHistory :> groupedQueryHistory {
				            :>> twinAttribute : Position default physicalBattery.p11.pos;
				           
				            :>> result : PositionQueryResult;
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
				        :>> result : Position[0..*];
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
	public void throwsException() {
		MappingException exception = assertThrows(MappingException.class, () -> super.testTopLevelDefinition());

		assertTrue(exception.getMessage().contains("multiplicity [0..*]"));
	}
}