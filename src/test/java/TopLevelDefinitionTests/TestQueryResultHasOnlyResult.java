package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.QueryResult;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
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

                      part physicalBattery :>> physicalTwin {
                          port p11 :> sensors {
                              attribute pos : Position :> measurements;
                          }
                      }

                      part descriptiveBattery :>> descriptiveModel {
                          attribute positionHistory : PositionQueryResult[0..*]
                                :> derivedAttributes
                                = PositionHistory(physicalBattery.p11.pos);
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

                  calc def PositionHistory :> GroupedHistoryQuery {
                      in :>> twinAttribute : Position[1];

                      return :>> result :
                            PositionQueryResult[0..*];
                  }
              }
              """);
	}

	@Test
	public void queryResultHasExactlyOneField() {
		QueryResult queryResult =
				named(QueryResult.class, "result");

		List<TwinAttributeUsage> fields =
				queryResult.getFields();

		assertEquals(1, fields.size());
	}

	@Test
	public void queryResultFieldHasExpectedName() {
		QueryResult queryResult =
				named(QueryResult.class, "result");

		TwinAttributeUsage field =
				queryResult.getFields().getFirst();

		assertEquals("result", field.getName());
	}

	@Test
	public void exactlyOneQueryResultIsMapped() {
		assertAmount(QueryResult.class, 2);
	}
}