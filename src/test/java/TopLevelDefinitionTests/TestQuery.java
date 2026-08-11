package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.GroupedHistoryQuery;
import org.example.Mapping.Interfaces.QueryHistory;
import org.example.Mapping.Interfaces.QueryResult;
import org.junit.jupiter.api.Test;


public class TestQuery extends AbstarctTest {


	@Test
	public void testGeneralQuery() {
		assertAmount(QueryHistory.class, 1);
		assertAmount(GroupedHistoryQuery.class, 1);
		assertAmount(QueryResult.class, 1);
	}
}
