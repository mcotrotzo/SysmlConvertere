package TopLevelDefinitionTests.MultiplicityRules;

import TopLevelDefinitionTests.AbstarctTest;
import org.example.Mapping.NewVersion.MappingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
public class LowerBoundOnNoneLibraryFeatures extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
				        port p11 :> sensors {
				        attribute pos[3] : Position :> measurements;
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
	public void lowerBoundTestShouldNotThrow() {
		assertAll(() -> super.testTopLevelDefinition());

	}

}