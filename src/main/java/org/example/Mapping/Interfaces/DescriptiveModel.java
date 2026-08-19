package org.example.Mapping.Interfaces;

import java.util.List;

public interface DescriptiveModel extends Model {

	List<TwinAttribute> getDerivedAttributes();

	List<QueryHistory> getQueriesHistory();

	List<GroupedHistoryQuery> getGroupQueriesHistory();

	List<DescriptiveStateMachine> getDescriptiveStateMachines();

	List<DescriptiveStrategy> getDescriptiveStrategies();

}
