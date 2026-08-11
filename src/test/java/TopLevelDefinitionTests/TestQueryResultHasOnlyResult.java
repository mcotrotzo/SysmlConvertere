package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.QueryResult;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestQueryResultHasOnlyResult extends AbstarctTest {

	@Override
	public Optional<String> getTestModel() {
		return Optional.of("""
				package Test {
				    private import TwinLibrary::*;
				    private import PositionThings::*;
				
				    part def Battery :> Twin {
				
				        port p11 :> sensors {
				        attribute pos : Position :> measurements;
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
				        :>> result : Position[0..*];
				    }
				}
				""");
	}

	@Test
	public void queryResultHasExactlyOneField() {
		QueryResult queryResult = named(QueryResult.class, "result");

		List<TwinAttribute> fields = queryResult.getFields();

		assertEquals( 1, fields.size());
	}

	@Test
	public void queryResultFieldHasExpectedName() {
		QueryResult queryResult = named(QueryResult.class, "result");

		TwinAttribute field = queryResult.getFields().get(0);

		assertEquals("result", field.getName());
	}

	@Test
	public void exactlyOneQueryResultIsMapped() {
		assertAmount(QueryResult.class, 1);
	}
}